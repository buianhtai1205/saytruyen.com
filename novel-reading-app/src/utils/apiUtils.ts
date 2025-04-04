import { toast, Bounce } from 'react-toastify';

export const apiCallWithToast = async (
    apiPromise: Promise<any>,
    successMessage: string,
    errorMessage: string
) => {
    return toast.promise(
        apiPromise,
        {
            pending: '🦄 Đang xử lý...',
            success: '🦄 ' + successMessage,
            error: '🦄 ' + errorMessage,
        },
        {
            position: 'top-right',
            autoClose: 2000,
            hideProgressBar: false,
            closeOnClick: false,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: 'dark',
            transition: Bounce,
        }
    );
};
