import React from 'react';
import clsx from 'clsx';
import styles from './StoryInfo.module.scss'; // Import SCSS module

const StoryInfo = ({ title, author, stats, latestChapter }) => {
    return (
        <div className={clsx(styles.storyInfo)}>
            <h1 className={clsx(styles.storyTitle)}>{title}</h1>
            <h2 className={clsx(styles.storyAuthor)}>{author}</h2>
            <div className={clsx(styles.storyStats)}>
                <span>{stats.chaptersPerWeek} Chs/tuần</span>
                <span>{stats.reads} lượt đọc</span>
                <span>{stats.recommendations} đề cử</span>
                <span>{stats.saved} cất giữ</span>
            </div>
            <div className={clsx(styles.storyButtons)}>
                <button className={clsx(styles.readButton)}>Đọc Truyện</button>
                <button className={clsx(styles.bookmarkButton)}>Đánh Dấu</button>
                <button className={clsx(styles.menuButton)}>Mục Lục</button>
                <button className={clsx(styles.ratingButton)}>Đánh Giá</button>
                <button className={clsx(styles.commentButton)}>Thảo Luận</button>
            </div>
            <div className={clsx(styles.latestChapter)}>
                <h3>Chương Mới</h3>
                {latestChapter.map((chapter, index) => (
                    <div key={index}>
                        <p>{chapter.title}</p>
                        <span>{chapter.timeAgo}</span>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default StoryInfo;