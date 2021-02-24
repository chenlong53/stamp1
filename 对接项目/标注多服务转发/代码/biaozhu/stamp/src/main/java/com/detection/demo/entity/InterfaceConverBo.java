package com.detection.demo.entity;

import java.util.List;
import java.util.Map;

/**
 * @author zhang
 */
public class InterfaceConverBo {
	private String respMsg;

	private String respId;

	private String respCode;

	private List<Map<String,String>> respInfo;

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getRespId() {
		return respId;
	}

	public void setRespId(String respId) {
		this.respId = respId;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public List<Map<String, String>> getRespInfo() {
		return respInfo;
	}

	public void setRespInfo(List<Map<String, String>> respInfo) {
		this.respInfo = respInfo;
	}
}
