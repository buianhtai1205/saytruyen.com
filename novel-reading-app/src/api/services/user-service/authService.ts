import apiClient from '@api/axiosConfig';
import { ApiResponse } from '@api/common/apiResponse';
import { userServicePath as basePath } from '@api/apiPaths';

export interface UserSignUpRequest {
    fullName: string;
    username: string;
    password: string;
    email: string;
    gender: number;
    address: string;
    imageUrl: string;
    backgroundUrl: string;
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse {
    token: string;
    refreshToken: string;
}

export const signUp = async (
    request: UserSignUpRequest
): Promise<ApiResponse<Boolean>> => {
    const response = await apiClient.post<ApiResponse<Boolean>>(
        `${basePath}/auth/sign-up`,
        request
    );
    return response.data;
};

export const login = async (
    request: LoginRequest
): Promise<ApiResponse<LoginResponse>> => {
    const response = await apiClient.post<ApiResponse<LoginResponse>>(
        `${basePath}/auth/login`,
        request
    );
    return response.data;
};

export const logout = async (token: string): Promise<ApiResponse<Boolean>> => {
    const response = await apiClient.post<ApiResponse<Boolean>>(
        `${basePath}/auth/logout?token=${token}`
    );
    return response.data;
};

export const refreshToken = async (
    token: string
): Promise<ApiResponse<LoginResponse>> => {
    const response = await apiClient.post<ApiResponse<LoginResponse>>(
        `${basePath}/auth/refresh-token?token=${token}`
    );
    return response.data;
};
