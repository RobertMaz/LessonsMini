import allClasses.VKUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Main {
    public static void main(String[] args) throws UnirestException, JsonProcessingException {
        String id = "mazrobert";
        String token = "https://api.vk.com/method/users.get?" +
                "user_ids=" + id + "&fields=bdate&" +
                "access_token=f0b32d48f0b32d48f0b32d48bbf0c1dfbbff0b3f0b32d48afb653a45a6b2197d24454e6&v=5.124";
        HttpResponse<JsonNode> response = Unirest.get(token).asJson();
        ObjectMapper objectMapper = new ObjectMapper();
//        VKUser vkUser = objectMapper.readValue(response.getStatusText(), VKUser.class);
        System.out.println(response.getHeaders().toString());
    }
}
