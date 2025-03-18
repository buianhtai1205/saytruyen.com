import apiClient from '../../axiosConfig';
import { ApiResponse } from '../../common/apiResponse';
import { PageableResponse } from '../../common/pageableResponse';
import { storyServicePath as basePath } from '../../apiPaths';

// Định nghĩa interface tương ứng với `StoryRequest`
export interface StoryRequest {
    name: string;
    slug: string;
    description: string;
    poster: string[];
    status: string;
    kind: string;
    chapterCount: number;
    chapterPerWeek: number;
    commentCount: number;
    reviewCount: number;
    reviewCore: number;
    authorId: number;
    viewCount: number;
    voteCount: number;
    wordCount: number;
    createdAt: string;
    updatedAt: string;
    newChapAt: string;
    publishedAt: string;
    others: string;
}

// Định nghĩa interface tương ứng với `StoryResponse`
export interface StoryResponse {
    id: string;
    name: string;
    slug: string;
    description: string;
    poster: string[];
    status: string;
    kind: string;
    chapterCount: number;
    chapterPerWeek: number;
    commentCount: number;
    reviewCount: number;
    reviewCore: number;
    authorId: number;
    viewCount: number;
    voteCount: number;
    wordCount: number;
    createdAt: string;
    updatedAt: string;
    newChapAt: string;
    publishedAt: string;
    others: string;
}

// Hàm gọi API để lấy danh sách stories
export const fetchStories = async (): Promise<
    ApiResponse<PageableResponse<StoryResponse>>
> => {
    const response = await apiClient.get<
        ApiResponse<PageableResponse<StoryResponse>>
    >(`${basePath}/story`);
    return response.data;
};

export const fetchRandomStories = async (): Promise<
    ApiResponse<Array<StoryResponse>>
> => {
    const respone = await apiClient.get<ApiResponse<Array<StoryResponse>>>(
        `${basePath}/story/get-random-story`
    );
    return respone.data;
};

// Hàm gọi API để lấy thông tin một story theo ID
export const fetchStoryById = async (
    id: string
): Promise<ApiResponse<StoryResponse>> => {
    const response = await apiClient.get<ApiResponse<StoryResponse>>(
        `${basePath}/story/${id}`
    );
    return response.data;
};

// Hàm gọi API để tạo một story mới
export const createStory = async (
    story: Partial<StoryRequest>
): Promise<StoryRequest> => {
    const response = await apiClient.post<StoryRequest>(
        `${basePath}/story/create`,
        story
    );
    return response.data;
};

// Hàm gọi API để cập nhật một story theo ID
export const updateStory = async (
    id: string,
    story: Partial<StoryRequest>
): Promise<StoryRequest> => {
    const response = await apiClient.put<StoryRequest>(
        `${basePath}/story/update/${id}`,
        story
    );
    return response.data;
};

// Hàm gọi API để xóa một story theo ID
export const deleteStory = async (id: string): Promise<void> => {
    await apiClient.put(`${basePath}/story/delete/${id}`);
};
