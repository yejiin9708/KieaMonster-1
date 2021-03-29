package org.tain.mapper;

import java.io.File;

import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;
import org.tain.utils._JsonPrint;
import org.tain.utils._StringTools;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Deprecated
@Getter
@Slf4j
public class _LnsMstInfo implements Cloneable {

	////////////////////////////////////////////////////////////////////////
	
	private String basePath;
	private String fileName;
	private String filePath;
	
	////////////////////////////////////////////////////////////////////////
	
	private long lastModfied;
	
	private String strJsonInfo;
	
	private String reqResType;
	
	// InfoNode
	private JsonNode infoNode;
	
	// head
	private JsonNode headBaseInfoNode;
	private JsonNode headDataInfoNode;
	
	// body
	private JsonNode bodyBaseInfoNode;
	private JsonNode bodyDataInfoNode;
	
	////////////////////////////////////////////////////////////////////////
	
	public _LnsMstInfo(String basePath, String fileName) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			this.basePath = basePath;
			this.fileName = fileName;
			this.filePath = this.basePath + File.separator + this.fileName;
		}
		
		this.updateInfo();
	}
	
	public _LnsMstInfo(String filePath) throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			File file = new File(filePath);
			this.basePath = file.getParent();
			this.fileName = file.getName();
			this.filePath = this.basePath + File.separator + this.fileName;
		}
		
		this.updateInfo();
	}
	
	////////////////////////////////////////////////////////////////////////
	
	public void setLength(String strLength) {
		((ObjectNode) this.headBaseInfoNode).put("length", strLength);
	}
	
	public void setExtHttpUrl(String extHttpUrl) {
		String httpUrl = this.headBaseInfoNode.get("extHttpUrl").asText();
		if (httpUrl == null || "".equals(httpUrl)) {
			((ObjectNode) this.headBaseInfoNode).put("extHttpUrl", "");
		} else {
			httpUrl = extHttpUrl + httpUrl;
			((ObjectNode) this.headBaseInfoNode).put("extHttpUrl", httpUrl);
		}
	}
	
	////////////////////////////////////////////////////////////////////////
	
	public boolean checkAndUpdate(File entry) throws Exception {
		//log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			if (this.lastModfied < entry.lastModified()) {
				this.updateInfo();
				return true;
			}
		}
		
		return false;
	}
	
	public String getJsonHead() throws Exception {
		_LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			lnsJsonNode = new _LnsJsonNode();
			lnsJsonNode.put("length", this.headBaseInfoNode.get("length").asText());
			lnsJsonNode.put("reqres", this.reqResType.substring(0, 4));
			lnsJsonNode.put("type", this.reqResType.substring(4));
			lnsJsonNode.put("trNo", "999999");
			lnsJsonNode.put("reqDate", _LnsNodeTools.getDate());
			lnsJsonNode.put("reqTime", _LnsNodeTools.getTime());
			lnsJsonNode.put("resTime", "");
			lnsJsonNode.put("resCode", "");
			lnsJsonNode.put("resMessage", "");
			lnsJsonNode.put("reserved", "");
		}
		return lnsJsonNode.toPrettyString();
	}
	
	public String getStreamHead() throws Exception {
		StringBuffer sb = new StringBuffer();
		
		_LnsJsonNode lnsJsonNode = null;
		if (Flag.flag) {
			lnsJsonNode = new _LnsJsonNode(this.getJsonHead());
		}
		
		if (Flag.flag) {
			JsonNode node = lnsJsonNode.get();
			this.headDataInfoNode.fieldNames().forEachRemaining((String fieldName) -> {
				String strInfo = this.headDataInfoNode.get(fieldName).asText();
				_LnsElementInfo info = new _LnsElementInfo(strInfo);
				if (info.isUsable()) {
					String data = node.get(fieldName).asText();
					info.setFormat("%-" + info.getLength() + "." + info.getLength() + "s");
					String fieldValue = String.format(info.getFormat(), data);
					sb.append(fieldValue);
				}
			});
		}
		
		return sb.toString();
	}
	
	public String getCStruct() throws Exception {
		return new _LnsCStruct(this).get();
	}
	
	////////////////////////////////////////////////////////////////////////
	
	private void updateInfo() throws Exception {
		log.info("KANG-20200721 >>>>> {} {}", CurrentInfo.get());
		
		if (Flag.flag) {
			File file = new File(this.filePath);
			this.lastModfied = file.lastModified() + 2*1000;
		}
		
		if (Flag.flag) {
			this.strJsonInfo = _StringTools.stringFromFile(this.filePath);
			this.infoNode = _JsonPrint.getInstance().getObjectMapper().readTree(this.strJsonInfo);
			this.headBaseInfoNode = this.infoNode.at("/__head_base");
			this.headDataInfoNode = this.infoNode.at("/__head_data");
			this.bodyBaseInfoNode = this.infoNode.at("/__body_base");
			this.bodyDataInfoNode = this.infoNode.at("/__body_data");
		}
		
		if (Flag.flag) {
			StringBuffer sb = new StringBuffer();
			sb.append(this.infoNode.at("/__head_base").get("reqres").asText());
			sb.append(this.infoNode.at("/__head_base").get("type").asText());
			this.reqResType = sb.toString();
		}
		
		//if (Flag.flag) log.info(">>>>> updated.infoNode = {}", this.infoNode.toPrettyString());
	}
	
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
}
