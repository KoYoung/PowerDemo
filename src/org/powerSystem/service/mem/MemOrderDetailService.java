package org.powerSystem.service.mem;

import org.powerSystem.entity.mem.MemOrderDetail;



@SuppressWarnings("hiding")
public interface MemOrderDetailService{
	public void saveMemOrderDetail(MemOrderDetail detail) throws RuntimeException;
	public MemOrderDetail queryAllOrderDetail(Integer orderNo);

}
