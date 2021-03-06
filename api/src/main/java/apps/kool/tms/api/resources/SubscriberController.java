package apps.kool.tms.api.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import apps.kool.tms.api.agregate.User;
import apps.kool.tms.api.errorhandling.EntityNotFoundException;
import apps.kool.tms.api.repository.UserRepository;
import apps.kool.tms.api.reqres.SubcriberResponse;
import apps.kool.tms.api.reqres.WorkFlowResponse;
import apps.kool.tms.api.utils.SectorName;

@RestController
@RequestMapping("/subscriber")

public class SubscriberController {
		
	@Autowired
    private UserRepository userRepository;
	
	@RequestMapping(value="/profile/{username}", method= RequestMethod.GET)
    public ResponseEntity<User> geSubscriberById(@PathVariable String username) {
		User user = userRepository.findByUsername(username).orElse(null);
    	return ResponseEntity.ok(user);
    }
	
	@RequestMapping(value="/profile", method= RequestMethod.PUT)
    public SubcriberResponse updateSubscriberProfile(@RequestBody User profile) {
		SubcriberResponse response = new SubcriberResponse();
		User userFromDB = userRepository.findByUsername(profile.getUsername()).orElse(null);
		if(userFromDB !=null) {
			userFromDB.setPrimaryAddress(profile.getPrimaryAddress());
			userFromDB.setFirstName(profile.getFirstName());
			userFromDB.setLastName(profile.getLastName());
			userFromDB.setMobileNumber(profile.getMobileNumber());
			userFromDB.setEmail(profile.getEmail());
			userFromDB.setHasSubscriberVerifiedInfo(true);
			userRepository.save(userFromDB);
			response.setWorkFlowResponse(WorkFlowResponse.builder().goToRoute("/profile").build());
			response.setUser(userFromDB);
			return response;
		}
		throw  new EntityNotFoundException("updateSubscriberProfile");
    	
    }
	
	@RequestMapping(value="/add", method= RequestMethod.POST)
    public SubcriberResponse addSubscriberProfile(@RequestBody User profile) {
		SubcriberResponse response = new SubcriberResponse();
		if(profile !=null && profile.getUsername() != null && profile.getPrimaryAddress() != null) {
			User user = User.builder().username(profile.getUsername())
					                  .primaryAddress(profile.getPrimaryAddress())
					                  .firstName(profile.getFirstName())
					                  .lastName(profile.getLastName())
					                  .mobileNumber(profile.getMobileNumber())
					                  .email(profile.getEmail())
					                  .hasSubscriberVerifiedInfo(true)
					                  .build();
					                  
			userRepository.save(user);
			response.setUser(user);
			return response;
		}
		throw  new EntityNotFoundException("addSubscriberProfile");
    	
    }
	
	@RequestMapping(value="/subscriber/sector", method= RequestMethod.GET)
    public ResponseEntity<Map<SectorName,List<SubcriberResponse>>> geSubscriberListSectorWise() {
		
    	return ResponseEntity.ok(null);
    }
}
