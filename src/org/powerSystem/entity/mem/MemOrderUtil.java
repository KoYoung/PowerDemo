package org.powerSystem.entity.mem;

public class MemOrderUtil {
	private MemOrder memOrder;

	
	public MemOrder getMemOrder() {
		return memOrder;
	}

	public void setMemOrder(MemOrder memOrder) {
		this.memOrder = memOrder;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	private String statusName;

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(int statusName) {
		if(statusName==1){
			this.statusName = "正常";
		}else{
			this.statusName = "锁定";
		}
		
	}
}
