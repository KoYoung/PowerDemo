package org.powerSystem.entity.mem;


public class MemberUtil {

	private MemMember memMember;

	public MemMember getMemMember() {
		return memMember;
	}

	public void setMemMember(MemMember memMember) {
		this.memMember = memMember;
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
