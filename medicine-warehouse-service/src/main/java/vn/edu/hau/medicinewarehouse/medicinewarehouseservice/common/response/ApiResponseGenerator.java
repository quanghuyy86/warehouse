package vn.edu.hau.medicinewarehouse.medicinewarehouseservice.common.response;

public class ApiResponseGenerator {
    private ApiResponseGenerator() {

    }

    public static ApiResponse<Void> success() {
        return new ApiResponse<>(ApiResponseCode.SUCCESS);
    }

    public static ApiResponse<Void> success(ApiResponseCode code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }

    public static <D> ApiResponse<D> success(ApiResponseCode code, String msg, D data) {
        return new ApiResponse<>(code, msg, data);
    }

    public static ApiResponse<Void> fail(ApiResponseCode code, String msg) {
        return new ApiResponse<>(code, msg, null);
    }

    public static <D> ApiResponse<D> fail(ApiResponseCode code, D data, String msg) {
        return new ApiResponse<>(code, msg, data);
    }
}
