package org.tain.mapper;

import org.tain.utils.Flag;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.extern.slf4j.Slf4j;

@Deprecated
@Slf4j
public class _LnsJsonToStream {

	private ObjectMapper objectMapper = new ObjectMapper();
	
	private _LnsMstInfo lnsMstInfo;
	
	private JsonNode infoNode;
	
	private JsonNode dataNode;
	
	private StringBuffer sb = new StringBuffer();
	
	//private int length = -1;
	
	public _LnsJsonToStream(_LnsMstInfo lnsMstInfo, JsonNode dataNode) {
		this.lnsMstInfo = lnsMstInfo;
		
		this.infoNode = (JsonNode) this.objectMapper.createObjectNode();
		((ObjectNode) this.infoNode).set("__head_data", this.lnsMstInfo.getHeadDataInfoNode());
		((ObjectNode) this.infoNode).set("__body_data", this.lnsMstInfo.getBodyDataInfoNode());
		if (!Flag.flag) log.info(">>>>> LnsJsonToStream.infoNode = " + this.infoNode.toPrettyString());
		
		this.dataNode = dataNode;
		if (!Flag.flag) log.info(">>>>> LnsJsonToStream.dataNode = " + this.dataNode.toPrettyString());
	}
	
	public _LnsJsonToStream(_LnsMstInfo lnsMstInfo, String strJson) throws Exception {
		this(lnsMstInfo, new ObjectMapper().readTree(strJson));
	}
	
	public String get() {
		//this.length = 0;
		
		if (Flag.flag) {
			// BEFORE:
			String reqRes = this.lnsMstInfo.getHeadBaseInfoNode().path("reqres").asText();
			String type = this.lnsMstInfo.getHeadBaseInfoNode().path("type").asText();
			//((ObjectNode) this.dataNode.at("/__head_data")).put("reqres", reqRes);
			//((ObjectNode) this.dataNode.at("/__head_data")).put("type", type);
			ObjectNode objectNode = (ObjectNode) this.dataNode.at("/__head_data");
			objectNode.put("reqres", reqRes);
			objectNode.put("type", type);
		}
		
		traverse(this.infoNode, "");
		
		if (Flag.flag) {
			// AFTER: stream length process
			// 전문길이(4) 제외
			//String strLength = String.format("%04d", this.length - 4);
			// 전문길이(4) 포함
			//String strLength = String.format("%04d", this.length);
			
			// String strLength = String.format("%04d", this.sb.length());  // 전문길이(4) 포함.
			String strLength = String.format("%04d", this.sb.length() - 4);  // 전문길이(4) 미포함.
			this.sb.delete(0, 4);
			this.sb.insert(0, strLength);
		}
		
		return this.sb.toString();
	}
	
	private void traverse(JsonNode node, String prefix) {
		if (Flag.flag) {
			if (node.isObject()) {
				traverseObject(node, prefix);
			} else if (node.isArray()) {
				traverseArray(node, prefix);
			} else {
				throw new RuntimeException("Not yet implements... [by Kiea Seok Kang]");
			}
		}
	}
	
	private void traverseObject(JsonNode node, String prefix) {
		if (Flag.flag) {
			node.fieldNames().forEachRemaining((String fieldName) -> {
				String _prefix = _LnsNodeTools.getPrefix(prefix, fieldName, "/");
				JsonNode childNode = node.get(fieldName);
				processNode(childNode, fieldName, _prefix);
				if (traversable(childNode)) {
					traverse(childNode, _prefix);
				}
			});
		}
	}
	
	private void traverseArray(JsonNode node, String prefix) {
		int arrSize = -1;
		if (Flag.flag) {
			String arrFieldName = prefix + "__arrSize";
			JsonNode arrFieldNameNode = this.lnsMstInfo.getBodyBaseInfoNode().path(arrFieldName);
			arrSize = arrFieldNameNode.asInt();
			if (Flag.flag) log.info(">>>>> {} = {}", arrFieldName, arrSize);
		}
		
		if (Flag.flag) {
			for (int index=0; index < arrSize; index++) {
				String _prefix = _LnsNodeTools.getPrefix(prefix, String.valueOf(index), "/");
				JsonNode itemNode = node.at("/0");
				processNode(itemNode, "arrayElements", _prefix);
				if (traversable(itemNode)) {
					traverse(itemNode, _prefix);
				}
			}
		}
	}
	
	private boolean traversable(JsonNode node) {
		return node.isObject() || node.isArray();
	}
	
	private void processNode(JsonNode node, String keyName, String prefix) {
		String line = null;
		if (traversable(node)) {
			line = String.format("%-30s   %s(%s)%n", prefix, keyName, node.getNodeType());
			//if (!Flag.flag) log.info(">>>>> " + line);
			//sb.append(line);
		} else {
			String strInfo = node.textValue();
			
			_LnsElementInfo info = new _LnsElementInfo(strInfo);
			if (info.isUsable()) {
				JsonNode dataNode = this.dataNode.at(prefix);
				
				Object data = null;
				if (dataNode.isTextual()) {
					data = dataNode.textValue();
				} else if (dataNode.isNumber()) {
					data = dataNode.numberValue();
				} else if (dataNode.isBoolean()) {
					data = dataNode.booleanValue();
				}
				
				if (data == null) {
					switch (info.getType()) {
					case "STRING":
					case "INT":
					case "LONG":
					case "DOUBLE":
					case "FLOAT":
					case "BOOLEAN":
						data = "";
						break;
					}
					info.setFormat("%" + info.getLength() + "s");
				}
				
				// TODO: thinking more
				
				//if (Flag.flag) System.out.printf(">>>>> %s [%s] %s%n", prefix, data, info.getFormat());
				line = String.format("%s %s [" + info.getFormat() + "]", prefix, info.getFormat(), data);
				if (Flag.flag) log.info(">>>>> {}", line);
				String fieldValue = String.format(info.getFormat(), data);
				this.sb.append(fieldValue);
				//this.length += info.getLength();
			}
		}
	}
}
