import React from 'react';
import clsx from 'clsx';
import styles from './StoryInfo.module.scss';

const StoryInfo = ({ title, author, stats, latestChapter }) => {
    return (
        <div className={clsx(styles.container)}>
            <main className={clsx(styles.main)}>
                <div className={clsx(styles.grid)}>
                    <div className={clsx(styles.coverColumn)}>
                        <img
                            src="https://static.cdnno.com/poster/dai-tan-co-thuy-hoang-tien-to-nghich-thien-vi-nhan-dao/300.jpg?1720424591"
                            alt="Book cover"
                            width={300}
                            height={400}
                            className={clsx(styles.coverImage)}
                        />
                    </div>
                    <div className={clsx(styles.infoColumn)}>
                        <h1 className={clsx(styles.title)}>
                            Từ Dạy Đồ Tần Thủy Hoàng Bắt Đầu, Ta Đại Tần Khai
                            Quốc Thủy Tổ
                        </h1>
                        <p className={clsx(styles.author)}>Vô Lượng 888</p>

                        <div className={clsx(styles.actionButtons)}>
                            <button
                                className={`${clsx(styles.button)} ${clsx(styles.primaryButton)}`}
                            >
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="currentColor"
                                    strokeWidth="2"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                >
                                    <path d="M4 19.5A2.5 2.5 0 0 1 6.5 17H20" />
                                    <path d="M6.5 2H20v20H6.5A2.5 2.5 0 0 1 4 19.5v-15A2.5 2.5 0 0 1 6.5 2z" />
                                </svg>
                                Đọc Truyện
                            </button>
                            <button className={clsx(styles.button)}>
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="currentColor"
                                    strokeWidth="2"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                >
                                    <path d="M4 15s1-1 4-1 5 2 8 2 4-1 4-1V3s-1 1-4 1-5-2-8-2-4 1-4 1z" />
                                    <line x1="4" y1="22" x2="4" y2="15" />
                                </svg>
                                Đánh Dấu
                            </button>
                            <button className={clsx(styles.button)}>
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="currentColor"
                                    strokeWidth="2"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                >
                                    <line x1="8" y1="6" x2="21" y2="6" />
                                    <line x1="8" y1="12" x2="21" y2="12" />
                                    <line x1="8" y1="18" x2="21" y2="18" />
                                    <line x1="3" y1="6" x2="3.01" y2="6" />
                                    <line x1="3" y1="12" x2="3.01" y2="12" />
                                    <line x1="3" y1="18" x2="3.01" y2="18" />
                                </svg>
                                Mục Lục
                                <span className={clsx(styles.badge)}>290</span>
                            </button>
                            <button className={clsx(styles.button)}>
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="currentColor"
                                    strokeWidth="2"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                >
                                    <polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2" />
                                </svg>
                                Đánh Giá
                                <span className={clsx(styles.badge)}>5</span>
                            </button>
                            <button className={clsx(styles.button)}>
                                <svg
                                    xmlns="http://www.w3.org/2000/svg"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    stroke="currentColor"
                                    strokeWidth="2"
                                    strokeLinecap="round"
                                    strokeLinejoin="round"
                                >
                                    <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z" />
                                </svg>
                                Thảo Luận
                                <span className={clsx(styles.badge)}>8</span>
                            </button>
                        </div>

                        <div className={clsx(styles.stats)}>
                            <div className={clsx(styles.statItem)}>
                                <div className={clsx(styles.statValue)}>
                                    111
                                </div>
                                <div className={clsx(styles.statLabel)}>
                                    Chương/tuần
                                </div>
                            </div>
                            <div className={clsx(styles.statItem)}>
                                <div className={clsx(styles.statValue)}>
                                    19426
                                </div>
                                <div className={clsx(styles.statLabel)}>
                                    Lượt đọc
                                </div>
                            </div>
                            <div className={clsx(styles.statItem)}>
                                <div className={clsx(styles.statValue)}>1</div>
                                <div className={clsx(styles.statLabel)}>
                                    Đề cử
                                </div>
                            </div>
                            <div className={clsx(styles.statItem)}>
                                <div className={clsx(styles.statValue)}>
                                    109
                                </div>
                                <div className={clsx(styles.statLabel)}>
                                    Cất giữ
                                </div>
                            </div>
                        </div>

                        <div className={clsx(styles.tags)}>
                            <span
                                className={`${clsx(styles.tag)} ${clsx(styles.tagAmber)}`}
                            >
                                Còn tiếp
                            </span>
                            <span
                                className={`${clsx(styles.tag)} ${clsx(styles.tagPurple)}`}
                            >
                                Huyền Huyễn
                            </span>
                            <span
                                className={`${clsx(styles.tag)} ${clsx(styles.tagGreen)}`}
                            >
                                Đông Phương Huyền Huyễn
                            </span>
                            <span
                                className={`${clsx(styles.tag)} ${clsx(styles.tagBlue)}`}
                            >
                                Xuyên Qua
                            </span>
                            <span
                                className={`${clsx(styles.tag)} ${clsx(styles.tagRed)}`}
                            >
                                Nhiệt Huyết
                            </span>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    );
};

export default StoryInfo;
