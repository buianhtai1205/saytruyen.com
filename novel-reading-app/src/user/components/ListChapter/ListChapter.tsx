import React from 'react';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import clsx from 'clsx';
import styles from './ListChapter.module.scss';
import { format, formatDistanceToNow } from 'date-fns';
import { is, vi } from 'date-fns/locale';
import { ChapterResponse } from '@api/services/story-service/chapterService';
import { PageableResponse } from '@api/common/pageableResponse';
import { ApiResponse } from '@api/common/apiResponse';

interface ListChapterProps {
    chapterListProps: PageableResponse<ChapterResponse> | ApiResponse<Array<ChapterResponse>> | undefined;
    parentIsModalOpen?: boolean;
}

const ListChapter: React.FC<ListChapterProps> = ({
    chapterListProps,
    parentIsModalOpen = false,
}) => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [activeTab, setActiveTab] = useState('DS Chương');
    const [newChapters, setNewChapters] = useState<ChapterResponse[]>([]);

    const { nameWithId } = useParams<{ nameWithId: string }>();

    const formatTimeAgo = (date: string) => {
        try {
            const dateObj = new Date(date);
            return formatDistanceToNow(dateObj, {
                addSuffix: true,
                locale: vi,
            })
                .replace('khoảng ', '')
                .replace('trong ', '');
        } catch (error) {
            return date;
        }
    };

    const formatTimeYmdHsm = (date: string) => {
        try {
            const dateObj = new Date(date);
            return format(dateObj, 'yyyy-MM-dd HH:mm:ss');
        } catch (error) {
            return date;
        }
    };

    useEffect(() => {
        let newChapters: ChapterResponse[] = [];
        if (chapterListProps && chapterListProps.data && chapterListProps.data.length > 0) {
            // Get last 3 elements using slice(-3)
            newChapters = chapterListProps.data.slice(-3).reverse();
        }
        console.log("effice");
        console.log(isModalOpen);
        setNewChapters(newChapters);
        setIsModalOpen(parentIsModalOpen);
    }, []);

    return (
        <div className={clsx(styles.container)}>
            {/* Latest Chapters Section */}
            <div className={clsx(styles.latestChapters)}>
                <div className={clsx(styles.header)}>
                    <h2 className={clsx(styles.title)}>CHƯƠNG MỚI</h2>
                    <button
                        onClick={() => setIsModalOpen(true)}
                        className={clsx(styles.viewAllButton)}
                    >
                        Xem tất cả
                        <svg
                            xmlns="http://www.w3.org/2000/svg"
                            viewBox="0 0 24 24"
                            fill="none"
                            stroke="currentColor"
                            strokeWidth="2"
                            strokeLinecap="round"
                            strokeLinejoin="round"
                            className={clsx(styles.icon)}
                        >
                            <polyline points="9 18 15 12 9 6"></polyline>
                        </svg>
                    </button>
                </div>

                <div className={clsx(styles.chapterList)}>
                    {newChapters.map((chapter) => (
                        <div
                            key={chapter.id}
                            className={clsx(styles.chapterItem)}
                        >
                            <a
                                href={`/truyen/${nameWithId}/chuong/${chapter.id}`}
                                className={styles.chapterLink}
                            >
                                {chapter.name}
                            </a>
                            <div className={styles.timestamp}>
                                {formatTimeAgo(chapter.createdAt)}
                            </div>
                        </div>
                    ))}
                </div>
            </div>

            {/* Modal */}
            {isModalOpen && (
                <div className={styles.modalOverlay}>
                    <div className={styles.modalContent}>
                        {/* Modal Header */}
                        <div className={styles.modalHeader}>
                            <h2 className={styles.modalTitle}>
                                Ta Mở Thật Sự Là Cô Nhi Viện, Không Phải Sát Thủ
                                Đường
                            </h2>
                            <button
                                onClick={() => setIsModalOpen(false)}
                                className={styles.closeButton}
                            >
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="currentColor"
                                    strokeWidth="2"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    className={styles.icon}
                                >
                                    <line x1="18" y1="6" x2="6" y2="18"></line>
                                    <line x1="6" y1="6" x2="18" y2="18"></line>
                                </svg>
                            </button>
                        </div>

                        {/* Tabs */}
                        <div className={styles.tabs}>
                            <div className={styles.downloadIcon}>
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="currentColor"
                                    strokeWidth="2"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                    className={styles.icon}
                                >
                                    <path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path>
                                    <polyline points="7 10 12 15 17 10"></polyline>
                                    <line x1="12" y1="15" x2="12" y2="3"></line>
                                </svg>
                            </div>
                            {['DS Chương', 'Đang Đọc', 'Đánh Dấu'].map(
                                (tab) => (
                                    <button
                                        key={tab}
                                        onClick={() => setActiveTab(tab)}
                                        className={`${styles.tab} ${activeTab === tab ? styles.activeTab : ''}`}
                                    >
                                        {tab}
                                    </button>
                                )
                            )}
                        </div>

                        {/* Content */}
                        <div className={styles.modalBody}>
                            {activeTab === 'DS Chương' && (
                                <div className={styles.chapterGrid}>
                                    {chapterListProps && chapterListProps.data.map((chapter) => (
                                        <div
                                            key={chapter.id}
                                            className={styles.chapterItem}
                                        >
                                            <a
                                                href={`/truyen/${nameWithId}/chuong/${chapter.id}`}
                                                className={styles.chapterLink}
                                            >
                                                {chapter.name}
                                            </a>
                                            <div className={styles.timestamp}>
                                                {formatTimeYmdHsm(
                                                    chapter.createdAt
                                                )}
                                            </div>
                                        </div>
                                    ))}
                                </div>
                            )}
                            {activeTab === 'Đang Đọc' && (
                                <div className={styles.emptyState}>
                                    Không có chương đang đọc
                                </div>
                            )}
                            {activeTab === 'Đánh Dấu' && (
                                <div className={styles.emptyState}>
                                    Không có chương được đánh dấu
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ListChapter;
