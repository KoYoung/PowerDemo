package hotelService;

import javax.xml.ws.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * 发布
 */
@Service
public class StartWebservice{
	
	@Autowired
	private HotelService hotelService;
	public void run(){
		String path ="http://192.168.11.202:9999/hotel";  
		Endpoint.publish(path, hotelService);
	}
}
