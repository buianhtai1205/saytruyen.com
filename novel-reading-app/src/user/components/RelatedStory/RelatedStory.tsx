import React from 'react';
import clsx from 'clsx';
import styles from './RelatedStory.module.scss';

interface Novel {
    id: number;
    title: string;
    coverUrl: string;
}

const novels: Novel[] = [
    {
        id: 1,
        title: 'An Cư Trưởng Sinh: Ta Tại Thẩm Sơn Thành Lập An Thế Gia Tộc',
        coverUrl:
            'https://static.cdnno.com/poster/an-cu-truong-sinh-ta-tai-tham-son-thanh-lap-an-the-gia-toc/300.jpg?1730220199',
    },
    {
        id: 2,
        title: 'Năm Tuổi Năm Đó Nuốt Vạn Xương Cốt, Tỉnh Lại Sau Giác Ngủ Đã Mất Dịch',
        coverUrl:
            'https://static.cdnno.com/poster/nam-tuoi-nam-do-nuot-van-xuong-cot-tinh-lai-sau-giac-ngu-da-mat-dich/300.jpg?1725880140',
    },
    {
        id: 3,
        title: 'Bắt Đầu Ngưu Gia Thôn, Ta Cùng Tẩu Tẩu Sống Nương Tựa Lẫn Nhau',
        coverUrl:
            'https://static.cdnno.com/poster/bat-dau-nguu-gia-thon-ta-cung-tau-tau-song-nuong-tua-lan-nhau/300.jpg?1714820350',
    },
    {
        id: 4,
        title: 'Chỉ Muốn Thường Thường Không Có Gì Là Ta Bị Nữ Đế Nghe Được Tiếng Lòng',
        coverUrl:
            'https://static.cdnno.com/poster/chi-muon-thuong-thuong-khong-co-gi-la-ta-bi-nu-de-nghe-duoc-tieng-long/300.jpg?1634271008',
    },
    {
        id: 5,
        title: 'Bắt Đầu Vô Địch, Chế Tạo Vạn Cổ Cự Thành',
        coverUrl:
            'https://static.cdnno.com/poster/bat-dau-vo-dich-che-tao-van-co-cu-thanh/300.jpg?1676952074',
    },
];

export default function RelatedStory() {
    return (
        <div className={clsx(styles.container)}>
            {/* Notification Banner */}
            <div className={clsx(styles.notification)}>
                <p>
                    Thông báo: Mọi người đọc thấy hay thì cho mình xin phiếu đề
                    cử với nha, hì vọng bộ này vào tốp đề cử, mong mọi người
                    giúp đỡ.
                </p>
            </div>

            {/* Section Header */}
            <div className={clsx(styles.sectionHeader)}>
                <div className={clsx(styles.title)}>
                    <span>CÙNG ĐĂNG BỞI</span>
                    <span className={clsx(styles.author)}>SHIN ĐẸP TRAI</span>
                </div>
                <a href="/#" className={clsx(styles.viewMore)}>
                    xem thêm
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
                </a>
            </div>

            {/* Novels Grid */}
            <div className={clsx(styles.novelsGrid)}>
                {novels.map((novel) => (
                    <div key={novel.id} className={clsx(styles.novelCard)}>
                        <div className={clsx(styles.imageWrapper)}>
                            <img
                                src={novel.coverUrl}
                                alt={novel.title}
                                className={clsx(styles.coverImage)}
                            />
                        </div>
                        <h3 className={clsx(styles.novelTitle)}>
                            {novel.title}
                        </h3>
                    </div>
                ))}
            </div>
        </div>
    );
}
