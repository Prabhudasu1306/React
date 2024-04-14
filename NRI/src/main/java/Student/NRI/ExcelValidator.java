package Student.NRI;

import org.springframework.web.multipart.MultipartFile;

public class ExcelValidator {

    public static boolean isExcelFile(MultipartFile file) {
        // Check if the file is not empty and its content type is an Excel file
        return !file.isEmpty() && isExcelContentType(file.getContentType());
    }

    private static boolean isExcelContentType(String contentType) {
        // Valid content types for Excel files
        return contentType != null &&
                (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                        contentType.equals("application/vnd.ms-excel"));
    }

    public static boolean isValidExcelExtension(String filename) {
        // Validate file extension for Excel files
        return filename != null && filename.toLowerCase().endsWith(".xlsx");
    }
}
