import React from 'react';
import clsx from 'clsx';
import styles from './ChapterNavigation.module.scss';

const ChapterNavigation: React.FC = () => {
    return (
        <div className={clsx(styles.navigationContainer)}>
            <button className={clsx(styles.navButton)}>
                &#9664; Chương trước
            </button>
            <span className={clsx(styles.separator)}>|</span>
            <button className={clsx(styles.navButton)}>
                &#9733; Chấm điểm
            </button>
            <span className={clsx(styles.separator)}>|</span>
            <button className={clsx(styles.navButton)}>
                &#127873; Tặng quà
            </button>
            <span className={clsx(styles.separator)}>|</span>
            <button className={clsx(styles.navButton)}>&#9873; Báo cáo</button>
            <span className={clsx(styles.separator)}>|</span>
            <button className={clsx(styles.navButton)}>&#127915; Đề cử</button>
            <span className={clsx(styles.separator)}>|</span>
            <button className={clsx(styles.navButton)}>
                Chương sau &#9654;
            </button>
        </div>
    );
};

export default ChapterNavigation;
