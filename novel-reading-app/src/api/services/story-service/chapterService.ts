import apiClient from '@api/axiosConfig';
import { ApiResponse } from '@api/common/apiResponse';
import { PageableResponse } from '@api/common/pageableResponse';
import { storyServicePath as basePath } from '@api/apiPaths';

// Định nghĩa interface tương ứng với `ChapterRequest`
export interface ChapterRequest {
    name: string;
    content: string;
    isLocked: boolean;
    unlockPrice: number;
    storyId: string;
    others: string;
}

// Định nghĩa interface tương ứng với `ChapterResponse`
export interface ChapterResponse {
    id: string;
    name: string;
    content: string;
    isLocked: boolean;
    unlockPrice: number;
    viewCount: number;
    wordCount: number;
    storyId: string;
    createdAt: string;
    updatedAt: string;
    publishedAt: string;
    others: string;
}

// Hàm gọi API để lấy danh sách chapters
export const fetchChapters = async (
    pageNumber: number,
    pageSize: number,
    storyId: string
): Promise<ApiResponse<PageableResponse<ChapterResponse>>> => {
    const response = await apiClient.get<
        ApiResponse<PageableResponse<ChapterResponse>>
    >(
        `${basePath}/chapter?pageNumber=${pageNumber}&pageSize=${pageSize}&storyId=${storyId}`
    );
    return response.data;
};

// Hàm gọi API để lấy thông tin một chapter theo ID
export const fetchChapterById = async (
    id: string
): Promise<ApiResponse<ChapterResponse>> => {
    const response = await apiClient.get<ApiResponse<ChapterResponse>>(
        `${basePath}/chapter/${id}`
    );
    return response.data;
};

// Hàm lấy mục lục
export const fetchSimpleChapter = async (
    storyId: string
): Promise<ApiResponse<Array<ChapterResponse>>> => {
    const response = await apiClient.get<ApiResponse<Array<ChapterResponse>>>(
        `${basePath}/chapter/list-chapter-simple/${storyId}`
    );
    return response.data;
};

// Hàm gọi API để tạo một chapter mới
export const createChapter = async (
    chapter: Partial<ChapterRequest>
): Promise<ChapterRequest> => {
    const response = await apiClient.post<ChapterRequest>(
        `${basePath}/chapter/create`,
        chapter
    );
    return response.data;
};

// Hàm gọi API để cập nhật một chapter theo ID
export const updatechapter = async (
    id: string,
    chapter: Partial<ChapterRequest>
): Promise<ChapterRequest> => {
    const response = await apiClient.put<ChapterRequest>(
        `${basePath}/chapter/update/${id}`,
        chapter
    );
    return response.data;
};

// Hàm gọi API để xóa một chapter theo ID
export const deleteChapter = async (id: string): Promise<void> => {
    await apiClient.put(`${basePath}/chapter/delete/${id}`);
};
