import apiClient from '../../axiosConfig';
import { ApiResponse } from '../../common/apiResponse';
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

export interface PageableResponse {
    pageNumber: number;
    pageSize: number;
    totalPages: number;
    totalElements: number;
    data: Array<StoryResponse>;
}

// Hàm gọi API để lấy danh sách stories
export const fetchStories = async (): Promise<
    ApiResponse<PageableResponse>
> => {
    const response = await apiClient.get<ApiResponse<PageableResponse>>(
        `${basePath}/story`
    );
    return response.data;
};

// Hàm gọi API để lấy thông tin một story theo ID
export const fetchStoryById = async (id: string): Promise<StoryResponse> => {
    const response = await apiClient.get<StoryResponse>(
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