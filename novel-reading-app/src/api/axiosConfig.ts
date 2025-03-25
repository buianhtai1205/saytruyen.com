import axios from 'axios';

// Tạo một instance Axios
const apiClient = axios.create({
    baseURL: process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api',
    timeout: 10000, // 10 giây
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true,
});

// Interceptor để thêm Authorization token
apiClient.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token');
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Add response interceptor to handle common errors
apiClient.interceptors.response.use(
    (response) => response,
    async (error) => {
        const originalRequest = error.config;

        // Handle different HTTP status codes
        if (error.response) {
            switch (error.response.status) {
                case 400:
                    // Bad Request
                    return Promise.reject({
                        message:
                            error.response.data.message ||
                            'Dữ liệu không hợp lệ',
                        status: 400,
                    });

                case 401:
                    // Unauthorized - clear tokens and redirect to login
                    localStorage.removeItem('token');
                    localStorage.removeItem('refreshToken');
                    // You might want to add logic here to redirect to login page
                    return Promise.reject({
                        message: 'Phiên đăng nhập đã hết hạn',
                        status: 401,
                    });

                case 403:
                    // Forbidden
                    return Promise.reject({
                        message: 'Bạn không có quyền truy cập',
                        status: 403,
                    });

                case 404:
                    // Not Found
                    return Promise.reject({
                        message: 'Không tìm thấy tài nguyên yêu cầu',
                        status: 404,
                    });

                case 500:
                case 501:
                case 502:
                case 503:
                    // Server Errors
                    return Promise.reject({
                        message: 'Đã có lỗi xảy ra, vui lòng thử lại sau',
                        status: error.response.status,
                    });

                default:
                    return Promise.reject({
                        message: 'Đã có lỗi xảy ra',
                        status: error.response.status,
                    });
            }
        }

        // Network errors or other issues
        if (error.request) {
            return Promise.reject({
                message: 'Không thể kết nối đến máy chủ',
                status: 0,
            });
        }

        return Promise.reject({
            message: 'Đã có lỗi xảy ra',
            status: 0,
        });
    }
);

export default apiClient;
