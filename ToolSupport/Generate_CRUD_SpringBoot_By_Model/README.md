# Spring Boot Code Generator by Python

Tool này giúp tự động sinh ra các file code cho dự án Spring Boot dựa trên file model entity. Với công cụ này, bạn có thể generate nhanh chóng các thành phần như:

- Controller (interface và implementation)
- Request
- Response
- Converter
- Service (interface và implementation)
- Repository

Tool sử dụng **Python 3** và thư viện **Jinja2** để render các file mẫu (template). Các template có thể được tùy chỉnh dễ dàng để phù hợp với yêu cầu dự án của bạn. Ngoài ra, tool hỗ trợ lựa chọn ORM: **Jpa** hoặc **Mongo**.

## Tính Năng

- **Tự động tạo thư mục:** Nếu đường dẫn chứa file đầu ra chưa tồn tại, tool sẽ tự động tạo.
- **Hỗ trợ ORM:** Tùy chọn `-orm` cho phép bạn chọn giữa Jpa và Mongo (mặc định là Jpa).
- **Xử lý lỗi chi tiết:** Mỗi bước của quá trình được bao bọc trong try/except để dễ dàng debug nếu có lỗi.
- **Tùy chỉnh template:** Dễ dàng tùy chỉnh các file template theo cấu trúc dự án của bạn.

## Yêu Cầu

- Python 3.x
- [Jinja2](https://pypi.org/project/Jinja2/)

## Cài Đặt

1. **Clone repository:**

    ```bash
    git clone <repository-url>
    cd <repository-directory>
    ```
2. **Cài đặt các gói cần thiết:**

    Sử dụng pip:
    ```bash
    pip install jinja2
    ```
## Cấu Trúc Dự Án

```bash
.
├── gen_crud.py                      # File chính của tool
├── read_model.py                    # Module parse file model entity
├── templates/                       # Thư mục chứa các template (Jinja2)
│   ├── controller_interface_template.java
│   ├── controller_template.java
│   ├── request_template.java
│   ├── response_template.java
│   ├── converter_template.java
│   ├── service_interface_template.java
│   ├── service_template.java
│   └── repository_interface_template.java
└── README.md                        # File hướng dẫn này
```

## Sử Dụng

Tool được chạy thông qua dòng lệnh. Các tham số cần thiết là:

-model: Đường dẫn đến file model entity (ví dụ: Banner.java).
Nếu không truyền đường dẫn tuyệt đối, tool sẽ tính theo thư mục hiện hành (nơi bạn chạy terminal).

-orm: (Tùy chọn) Loại ORM sử dụng: Jpa hoặc Mongo (mặc định là Jpa).

## Ví dụ Sử Dụng

Sinh code với file model Banner.java và ORM Jpa (mặc định):

```bash
python3 gen_crud.py -model=Banner.java
```

Sinh code với file model Banner.java và ORM Mongo:

```bash
python3 gen_crud.py -model=Banner.java -orm=Mongo
```

## Chạy Tool Từ Bất Kỳ Vị Trí Nào

Để chạy tool từ bất kỳ đâu, bạn có thể:

1. **Thêm thư mục chứa file generate.py vào biến PATH:**

    Mở file cấu hình shell (ví dụ ~/.bashrc hoặc ~/.zshrc) và thêm dòng:

    ```bash
    export PATH=$PATH:/path/to/your/tool
    ```

    Sau đó tải lại file cấu hình:

    ```bash
    source ~/.bashrc
    ```

2. **Tạo alias (ví dụ: gen-crud):**

    Mở file cấu hình shell và thêm dòng:

    ```bash
    alias gen-crud="/path/to/your/tool/generate.py"
    ```

    Đừng quên cấp quyền thực thi cho file gen_crud.py:

    ```bash
    chmod +x /path/to/your/tool/generate.py
    ```

    Sau đó, bạn có thể chạy tool bằng:

    ```bash
    gen-crud -model=Banner.java -orm=Jpa
    ```

## Xử Lý Lỗi

Tool được tích hợp xử lý lỗi chi tiết:

- Nếu không tìm thấy thư mục đầu ra, tool sẽ tự động tạo.

- Nếu có lỗi trong quá trình lấy template, render template hoặc ghi file, thông báo lỗi sẽ được in ra và quá trình sẽ dừng lại tại bước đó.

## Modify

Tool được viết nhằm mục đích support dự án saytruyen.com.

Các file template hiện tại đang được cấu hình trong thư mục “/templates”. 

Các bạn có thể check và modify theo template hoặc style dự án của mình.

Template của mình có sử dụng Common do mình viết để support dự án (ApiResponse, ExceptionHandle, PageableResponse, ....). 

Nếu bạn quan tâm có thể mvn cài đặt common
Refer: https://mvnrepository.com/artifact/io.github.buianhtai1205/saytruyen-common-service

## Liên Hệ

Nếu bạn có câu hỏi, góp ý hoặc cần hỗ trợ, vui lòng liên hệ:

- Email: buianhtai2017tq@gmail.com

- GitHub: buianhtai1205

## License

Tool này được cấp phép theo MIT License.
