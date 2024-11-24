import axios, { AxiosError } from 'axios';

// Tạo một instance Axios
const apiClient = axios.create({
    baseURL: process.env.REACT_APP_API_BASE_URL || 'http://localhost:8080/api',
    timeout: 10000, // 10 giây
    headers: {
        'Content-Type': 'application/json',
    },
});

// Interceptor để thêm Authorization token
apiClient.interceptors.request.use(
    (config) => {
        // const token = localStorage.getItem('accessToken');
        // if (token) {
        //     config.headers.Authorization = `Bearer ${token}`;
        // }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

// Interceptor để xử lý lỗi
apiClient.interceptors.response.use(
    (response) => response,
    (error: AxiosError) => {
        if (error.response) {
            // Xử lý lỗi 4xx, 5xx
            console.error(
                `API Error [${error.response.status}]:`,
                error.response.data
            );
        } else {
            console.error('Network Error:', error.message);
        }
        return Promise.reject(error);
    }
);

export default apiClient;
