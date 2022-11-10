package emreb.Models;

import com.google.gson.JsonObject;

public class Response {
    private static byte[] getAsByte(JsonObject response) {
        return response.toString().getBytes();
    }

    private static JsonObject toJson(String message, String status) {
        JsonObject response = new JsonObject();
        response.addProperty("message", message);
        response.addProperty("status", status);
        return response;
    }

    public static byte[] Error(String message, String status) {
        JsonObject errorResponse = Response.toJson(message, status);
        return getAsByte(errorResponse);
    }

    public static byte[] Error() {
        JsonObject errorResponse = Response.toJson("Error: Invalid Request Type.", "Failed");
        return getAsByte(errorResponse);
    }

    public static byte[] Success(String message, String status) {
        JsonObject successResponse = Response.toJson(message, status);
        return  getAsByte(successResponse);
    }

    public static byte[] Success() {
        JsonObject successResponse = Response.toJson("Operation completed successfully.", "Success");
        return  getAsByte(successResponse);
    }
}
