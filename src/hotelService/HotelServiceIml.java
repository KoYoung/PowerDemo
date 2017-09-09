package hotelService;

import javax.jws.WebService;

import org.powerSystem.entity.mem.MemCard;
import org.powerSystem.service.mem.MemCardService;
import org.powerSystem.service.mem.MemRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@WebService(endpointInterface = "hotelService.HotelService")
public class HotelServiceIml implements HotelService {
 
	@Autowired
	private MemRankService rankService;
	@Autowired
	private MemCardService cardService;
	@Autowired
	private MemCardService memCardService;
	private MemCard mc;
	public String getdiscount(String cardno) {
		try {
			float discount = rankService.query(cardService.query(cardno).getCardLevel());
			String result = "0"+(int)(discount*10);
			return result;
		} catch (Exception e) {
			return "100";
		}
	}

	public String getintegral(String cardno, float money) {
		try {
			Integer discount =(int) (money/10);
			 mc=memCardService.query(cardno);
			Integer aaa=mc.getCardScore()+discount;
			cardService.updatecardscore(cardno, aaa);
			return "0";
		} catch (Exception e) {
			return "1";
		} 
	}

	public MemCardService getMemCardService() {
		return memCardService;
	}

	public void setMemCardService(MemCardService memCardService) {
		this.memCardService = memCardService;
	}

	public MemRankService getRankService() {
		return rankService;
	}

	public void setRankService(MemRankService rankService) {
		this.rankService = rankService;
	}

	public MemCard getMc() {
		return mc;
	}

	public void setMc(MemCard mc) {
		this.mc = mc;
	}

}
