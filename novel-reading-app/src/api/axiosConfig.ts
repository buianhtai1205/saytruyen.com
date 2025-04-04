import axios from 'axios';

// Tạo một instance Axios cơ bản không có authorization
const apiClient = axios.create({
    baseURL: process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api',
    timeout: 10000, // 10 giây
    headers: {
        'Content-Type': 'application/json',
    },
    withCredentials: true,
});

// Tạo hàm tạo instance có authorization
export const createAuthApiClient = () => {
    const authClient = axios.create({
        baseURL:
            process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api',
        timeout: 10000, // 10 giây
        headers: {
            'Content-Type': 'application/json',
        },
        withCredentials: true,
    });

    // Thêm interceptor authorization chỉ cho authClient
    authClient.interceptors.request.use(
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

    // Copy response interceptor từ apiClient
    authClient.interceptors.response.use(
        (response) => response,
        async (error) => {
            // Handle different HTTP status codes
            if (error.response) {
                const status = error.response.status;

                switch (status) {
                    case 400:
                    case 401:
                    case 403:
                        return Promise.reject(error.response.data);

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
                            status: status,
                        });

                    default:
                        return Promise.reject({
                            message: 'Đã có lỗi xảy ra',
                            status: status,
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

    return authClient;
};

// Tạo instance có auth sẵn
export const authApiClient = createAuthApiClient();

// Add response interceptor to handle common errors cho apiClient không có auth
apiClient.interceptors.response.use(
    (response) => response,
    async (error) => {
        // Handle different HTTP status codes
        if (error.response) {
            const status = error.response.status;

            switch (status) {
                case 400:
                case 401:
                case 403:
                    return Promise.reject(error.response.data);

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
                    // Server Errors - Redirect to page500
                    console.log('Server Errors - Redirect to page500');
                    window.location.href = '/internal-server-error';
                    return Promise.reject({
                        message: 'Đã có lỗi xảy ra, vui lòng thử lại sau',
                        status: status,
                    });

                default:
                    return Promise.reject({
                        message: 'Đã có lỗi xảy ra',
                        status: status,
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
