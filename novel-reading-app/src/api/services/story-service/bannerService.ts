import apiClient from '../../axiosConfig';
import { ApiResponse } from '../../common/apiResponse';
import { PageableResponse } from '../../common/pageableResponse';
import { storyServicePath as basePath } from '../../apiPaths';

// Định nghĩa interface tương ứng với `BannerRequest`
export interface BannerRequest {
    name: string;
    url: string;
    bannerType: string;
    bannerLinkedId: string;
    bgDesktop: string;
    bannerDesktop: string;
    bannerMobile: string;
    others: string;
}

// Định nghĩa interface tương ứng với `BannerResponse`
export interface BannerResponse {
    id: string;
    name: string;
    url: string;
    bannerType: string;
    bannerLinkedId: string;
    bgDesktop: string;
    bannerDesktop: string;
    bannerMobile: string;
    createdAt: Date;
    updatedAt: Date;
    others: string;
}

// Hàm gọi API để lấy danh sách Banners
export const fetchBanners = async (): Promise<
    ApiResponse<PageableResponse<BannerResponse>>
> => {
    const response = await apiClient.get<
        ApiResponse<PageableResponse<BannerResponse>>
    >(`${basePath}/banner`);
    return response.data;
};

// hàm gọi API lấy random 10 banners
export const fetchRandomBanners = async (): Promise<
    ApiResponse<Array<BannerResponse>>
> => {
    const response = await apiClient.get<ApiResponse<Array<BannerResponse>>>(
        `${basePath}/banner/get-random-banner`
    );
    return response.data;
};
