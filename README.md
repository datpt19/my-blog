# 🦄 Unicorns Backend

![Spring Boot](https://img.shields.io/badge/SpringBoot-3.4.4-brightgreen.svg?style=flat-square) ![Java](https://img.shields.io/badge/Java-17-blue.svg?style=flat-square) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14+-blue?style=flat-square) ![Redis](https://img.shields.io/badge/Redis-6+-red?style=flat-square) ![License](https://img.shields.io/badge/License-MIT-yellow.svg?style=flat-square)

## 🚀 Giới thiệu
**Unicorns Backend** là một hệ thống API RESTful viết bằng Spring Boot (Java 17), áp dụng Clean Architecture. Dự án được chia thành các lớp rõ ràng: `controller`, `service`, `repository`, `dto`, `entity`, PostgreSQL và có cấu trúc Exception riêng biệt.

## 📁 Cấu trúc dự án
```bash
src
└── main
    └── java
        └── unicorns.backend
            ├── config        # Cấu hình chung
            ├── controller    # REST API endpoints
            ├── domain        # Business logic / interface
            ├── dto           # DTO truyền dữ liệu
            ├── entity        # Entity ánh xạ database
            ├── exception     # Xử lý ngoại lệ
            ├── repository    # Truy vấn DB
            ├── service       # Xử lý logic
            └── util          # Hàm tiện ích

```

## 🧰 Công nghệ sử dụng

| Thành phần        | Phiên bản / Công nghệ      | Ghi chú                              |
|------------------|----------------------------|--------------------------------------|
| 💻 Ngôn ngữ       | Java 17                    | Phiên bản LTS, hỗ trợ tính năng hiện đại |
| 🌱 Framework      | Spring Boot 3.4.4          | Cấu hình nhanh, hỗ trợ AOT và Native |
| 📦 Quản lý build  | Maven                      | Quản lý dependencies, lifecycle      |
| 🧪 Testing        | JUnit, Mockito             | Viết unit test và mock đơn giản      |
| 📚 API Docs       | Springdoc OpenAPI / Swagger| Tự động sinh tài liệu REST API       |
| 🐳 DevOps         | Docker, Docker Compose     | Triển khai nhanh môi trường phụ trợ  |

## ▶️ Hướng dẫn chạy dự án

### ⚙️ Yêu cầu hệ thống
- Java 17 (LTS)
- Maven 3.6+
- Docker & Docker Compose

### 🚀 Các bước khởi chạy
```bash
# 1. Clone dự án
git clone https://github.com/phanthanhdat1902/springboot-java17-codebase.git
cd springboot-java17-codebase

# 2. Cấu hình biến môi trường nếu cần (application-deploy.yml)
Viết file .env hoặc sửa trong file application-deploy.yml các tham số database

# 3. Khởi động PostgreSQL bằng Docker (nếu chưa có sẵn)
cd deploy
docker-compose up -d

# 4. Build project
mvn clean install

# 5. Chạy với profile local
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

## 📄 Tài liệu API

Hệ thống sử dụng **Springdoc OpenAPI** để tự động sinh tài liệu RESTful API.

- 🔗 **Swagger UI**: [http://localhost:8080/backend/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)  
  → Giao diện trực quan để kiểm thử và khám phá các API.

- 🔗 **OpenAPI JSON**: [http://localhost:8080/backend/v3/api-docs](http://localhost:8080/v3/api-docs)  
  → Dành cho tích hợp vào Postman, Stoplight, hoặc các công cụ CI/CD.

📌 Tài liệu bao gồm:
- Chi tiết các endpoint (GET, POST, PUT, DELETE…)
- Mô tả tham số đầu vào/đầu ra
- Mã lỗi và thông điệp phản hồi
- Các schema (DTO, entity) liên quan

---

## 👤 Tác giả & Đóng góp

### 🧑‍💻 Tác giả chính
| Tên                | GitHub Profile                                      | Vai trò            |
|--------------------|-----------------------------------------------------|---------------------|
| **Phan Thành Đạt** | [@phanthanhdat1902](https://github.com/datpt19) | Backend Developer, Maintainer |

### 🤝 Đóng góp vào dự án
Chúng tôi luôn chào đón các bạn tham gia đóng góp để cải thiện hệ thống!

#### Cách đóng góp:
```bash
# 1. Fork repository
# 2. Tạo nhánh mới cho tính năng/sửa lỗi
git checkout -b feature/your-feature-name

# 3. Commit thay đổi
git commit -m "Add your description"

# 4. Push và tạo Pull Request
git push origin feature/your-feature-name
```

## ✅ TODO

- [ ] 🧪 Viết unit test cho các service chính
- [ ] 📦 Triển khai CI/CD với GitHub Actions
- [ ] 📊 Tích hợp Prometheus + Grafana để theo dõi hiệu năng
- [ ] 🔐 Bổ sung kiểm tra phân quyền cho các endpoint nhạy cảm
- [ ] 📝 Viết tài liệu hướng dẫn triển khai thực tế (Docker Compose hoặc Kubernetes)
- [ ] 📁 Tối ưu cấu trúc module theo chuẩn clean architecture
- [ ] 💬 Thêm tính năng logging chi tiết cho tracing/debug
- [ ] 📚 Mở rộng README với diagram kiến trúc và luồng xử lý chính


📬 Đừng ngại mở issue nếu bạn gặp lỗi, có ý tưởng hoặc đề xuất cải tiến!

🌟 Nếu bạn thấy dự án hữu ích, hãy để lại ⭐ để ủng hộ tinh thần nhé!
