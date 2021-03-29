package org.tain.mapper;

import org.tain.utils.enums._OptionType;

import lombok.Data;

@Deprecated
@Data
public class _LnsElementInfo {

	private boolean usable = true;
	private int length = -1;
	private String type = "string";
	private String leading = "";
	private String defValue = "";
	private String format = "%s";
	private String comment = "";
	
	private int optionType = 0;
	
	/*
	 *   length     = L:4,T:0int
	 *   reqres     = L:4,T:string,D:0700
	 *   type       = L:3,T:string,D:100
	 *   code       = L:3,T:string
	 *   messaage   = L:20,T:string
	 *   reserved   = L:66,T:string
	 *   dummy      = L:-100,T:string
	 */
	public _LnsElementInfo(String strInfo) {
		//String[] split = strInfo.split("\\w*,\\w*");
		String[] split = strInfo.split(",");
		for (int i=0; i < split.length; i++) {
			String info = split[i];
			switch (info.charAt(0)) {
			case 'L':
				this.length = Integer.valueOf(info.substring(2).trim());
				this.usable = this.length >= 0 ? true : false;
				break;
			case 'T':
				if (info.charAt(2) == '0') {
					this.leading = "0";
					this.type = info.substring(3).toUpperCase();
				} else {
					this.type = info.substring(2).toUpperCase();
				}
				break;
			case 'D':
				this.defValue = info.substring(2);
				break;
			case 'C':
				this.comment = info.substring(2);
				break;
			case 'O':
				this.optionType = this.optionType | _OptionType.valueOf(info.substring(2).trim()).getValue();
				break;
			default:
				throw new RuntimeException("Couldn't be parsing the string of info [" + strInfo + "].");
			}
		}
		switch (this.type) {
		case "INT":
			this.format = "%" + this.leading + this.length + "d";
			if (this.length == 0)
				this.format = "%d";
			break;
		case "LONG":
			this.format = "%" + this.leading + this.length + "d";
			if (this.length == 0)
				this.format = "%ld";
			break;
		case "STRING":
			this.format = "%-" + this.length + '.' + this.length + "s";
			if (this.length == 0)
				this.format = "%s";
			break;
		case "BOOLEAN":
			this.format = "%-" + this.length + '.' + this.length + "s";
			if (this.length == 0)
				this.format = "%s";
			break;
		default:
			this.format = "%-" + this.length + '.' + this.length + "s";
			if (this.length == 0)
				this.format = "%s";
			break;
		}
	}
}
