import re

def parse_model_file(model_file_path):
    """
    Đọc file model và trích xuất:
      - entityName: tên class (ví dụ: Banner)
      - fields: danh sách các field, mỗi field có dạng dict với keys:
          - type: kiểu dữ liệu
          - name: tên field
          - isKey: True nếu field có annotation @Id, ngược lại False
      - idType: kiểu của field được đánh dấu @Id (mặc định là "String" nếu không tìm thấy)
      - packageName: package name được khai báo trong file model
    """
    with open(model_file_path, 'r', encoding='utf-8') as f:
        content = f.read()

    # Lấy tên class (giả sử định nghĩa là "public class <EntityName>")
    class_match = re.search(r'public\s+class\s+(\w+)', content)
    if not class_match:
        raise Exception("Không tìm thấy khai báo class trong file model.")
    entity_name = class_match.group(1)

    lines = content.splitlines()
    fields = []
    id_next = False
    package_name = "com.example.demo"

    for line in lines:
        line = line.strip()
        # Nếu gặp annotation @Id, đánh dấu rằng field tiếp theo là key
        if line.startswith("@Id"):
            id_next = True
            continue
        # Tìm dòng khai báo field, ví dụ: "private String id;"
        field_match = re.match(r'private\s+(\S+)\s+(\w+)\s*;', line)
        if field_match:
            field_type, field_name = field_match.groups()
            field = {"type": field_type, "name": field_name, "isKey": id_next}
            fields.append(field)
            id_next = False
        # Lấy package name
        if line.startswith("package"):
                # Loại bỏ từ khóa 'package' và ký tự ';'
                pkg_line = line.replace("package", "").replace(";", "").strip()
                # Nếu package có đuôi .model, loại bỏ phần đó
                if pkg_line.endswith(".model"):
                    package_name = pkg_line.rsplit(".", 1)[0]

    # Xác định idType: tìm field đầu tiên có isKey=True
    key_field = next((f for f in fields if f["isKey"]), None)
    id_type = key_field["type"] if key_field else "String"
    
    return {
        "entity_name": entity_name,
        "id_type": id_type,
        "fields": fields,
        "package_name": package_name
    }

if __name__ == "__main__":
    model_file_path = "Banner.java"
    context = parse_model_file(model_file_path)
    print(context)