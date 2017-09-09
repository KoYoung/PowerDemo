package hotelService;
import javax.jws.WebService;

@WebService
public interface HotelService {

	public String getdiscount(String cardno);//根据卡号 查出折扣
	
	public String getintegral(String cardno , float money);//根据折扣应付价钱
}
