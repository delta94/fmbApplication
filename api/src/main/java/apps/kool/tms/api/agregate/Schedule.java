package apps.kool.tms.api.agregate;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@Document(collection= "schedule")
public class Schedule {
	
	@JsonIgnore
	private  @Id  ObjectId id;
	@JsonFormat(pattern="yyyy-MM-dd")
	@Indexed(unique = true)
	private java.util.Date dailyDate;
	@DBRef
	private Menu menu;
	private boolean noMeal;
	private String  noMealReason;
	@DBRef
	private Contributor[] contributors;
	private ArrayList<InstructionMessage> instructionsForSubscriber;
	//private List<InstructionMessage> instructionsForOperations;
	//private List<InstructionMessage> instructionsForCatrer;
	
 }
