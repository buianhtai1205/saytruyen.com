import React from 'react';
import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import clsx from 'clsx';
import styles from './ListChapter.module.scss';
import { format, formatDistanceToNow } from 'date-fns';
import { vi } from 'date-fns/locale';
import { ChapterResponse } from '@api/services/story-service/chapterService';
import { PageableResponse } from '@api/common/pageableResponse';

interface Chapter {
    id: number;
    title: string;
    timestamp: string;
}

const chapters: Chapter[] = [
    { id: 288, title: 'Gặp rủi ro?', timestamp: '11 phút trước' },
    { id: 287, title: 'Công hãn hậu đỉnh!', timestamp: '11 phút trước' },
    {
        id: 286,
        title: 'Hắn là Đế Quân địch tôn tử, đưa đến ngọc Diệp Dương lát cỏ hậu bảo!',
        timestamp: 'một ngày trước',
    },
];

const allChapters: Chapter[] = Array.from({ length: 14 }, (_, i) => ({
    id: i + 1,
    title:
        i === 0
            ? 'Kỳ quái cô nhi bởi đường hệ thống'
            : i === 1
              ? 'Kỳ quái từ đầu 【thể phách cường kiện】【khéo tay】'
              : i === 2
                ? 'Gánh nặng đường xa! Hệ thống thương thành!'
                : i === 3
                  ? 'Mỗi ngày kết toán! Thật sự là trời sinh thần lực?'
                  : i === 4
                    ? 'Lồng giam'
                    : i === 5
                      ? 'Trần Diệp: Bản tọa để người ba chiều! Ra tay đi!'
                      : i === 6
                        ? 'Trùng phùng'
                        : i === 7
                          ? 'Hoàn thành nhiệm vụ! Mới từ đầu! Mở ra phòng sách!'
                          : i === 8
                            ? 'Gió thổi báo giông báo sắp đến'
                            : i === 9
                              ? 'Mỗi ngày kết toán! Một ngày mới!'
                              : i === 10
                                ? 'Đại Minh, người về sau muốn làm cái này à?'
                                : i === 11
                                  ? 'Vội vàng hơn thắng'
                                  : i === 12
                                    ? 'Chỉ pháp tiêu thành! Ngọc la sát đa mắc cấu!'
                                    : 'Đường Phong hạ lạc! Mai hoa chậm!',
    timestamp: i < 10 ? '2024-04-12 11:35:54' : '2024-04-12 20:25:12',
}));

interface ChapterInfoProps {
    chapterListProps: PageableResponse<ChapterResponse>;
}

const ListChapter: React.FC<ChapterInfoProps> = ({ chapterListProps }) => {
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
        if (chapterListProps.data && chapterListProps.data.length > 0) {
            // Get last 3 elements using slice(-3)
            newChapters = chapterListProps.data.slice(-3).reverse();
        }
        console.log(newChapters);
        setNewChapters(newChapters);
    }, [chapterListProps]);

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
                                    {chapterListProps.data.map((chapter) => (
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
