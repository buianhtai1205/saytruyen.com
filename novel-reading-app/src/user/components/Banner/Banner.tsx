import React from 'react';
import clsx from 'clsx';
import styles from './Banner.module.scss';

// Định nghĩa kiểu props
interface BannerProps {
    imageUrl: string;
    linkUrl?: string;
    altText?: string;
}

// Sử dụng kiểu đã định nghĩa
const Banner: React.FC<BannerProps> = ({
    imageUrl,
    linkUrl = '/story-detail',
    altText = 'Banner Image',
}) => (
    <div className={clsx(styles.bannerWrapper)}>
        <a href={linkUrl} className={clsx(styles.bannerLink)}>
            <img
                src={imageUrl}
                alt={altText}
                className={clsx(styles.bannerImage)}
            />
        </a>
    </div>
);

export default Banner;
