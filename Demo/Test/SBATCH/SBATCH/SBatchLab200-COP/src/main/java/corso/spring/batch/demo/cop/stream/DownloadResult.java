package corso.spring.batch.demo.cop.stream;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DownloadResult {

	
	SUCCESS("success"),ABORTED("aborted"), FAILURE("failure");

	  private String text;

	  private DownloadResult(String text) {
	    this.text = text;
	  }

	  public String getText() {
	    return text;
	  }

	  public void setText(String text) {
	    this.text = text;
	  }

	  public static DownloadResult fromText(String text) {
		  DownloadResult resultIfAny = string2Enums.get(text);
	    if (resultIfAny == null) {	     
	      throw new IllegalStateException(text +" is not a valid DownloadResult");
	    }
	    return resultIfAny;
	  }

	  private static Map<String, DownloadResult> string2Enums = initMap();

	  private static Map<String, DownloadResult> initMap() {
	    Map<String, DownloadResult> map = new HashMap<String, DownloadResult>();
	    map.put(SUCCESS.getText(), SUCCESS);
	    map.put(ABORTED.getText(), ABORTED);
	    map.put(FAILURE.getText(), FAILURE);	  
	    return map;
	  }
	
}
