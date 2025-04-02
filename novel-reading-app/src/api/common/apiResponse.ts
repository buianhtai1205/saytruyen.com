export interface ApiResponse<T> {
    code: number;
    messageId: string;
    message: string;
    data: T;
}
