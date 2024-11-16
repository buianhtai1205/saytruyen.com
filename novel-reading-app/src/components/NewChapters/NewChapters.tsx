import React from 'react';
import styles from './NewChapters.module.scss';

const NewChapters = () => {
    const chapters = [
        {
            genre: 'Huyền Huyễn',
            title: 'Toàn Dân Tu Tiên: Gặp Trăm Lần Thưởng Cho',
            author: 'Phi Tưởng Dịch Bạch Điều A',
            chapter: 'Chương 992',
            time: 'vài giây trước',
        },
        {
            genre: 'Đô Thị',
            title: 'Say Rượu Mất Không Chế, Bị Ép Cưới Gặp Kinh Vòng Đại Tiểu Thư',
            author: 'Giang Hạo Thần',
            chapter: 'Chương 317',
            time: 'một phút trước',
        },
        {
            genre: 'Huyền Huyễn',
            title: 'Bắt Đầu Đưa Tặng Thiên Sinh Thần Lực',
            author: 'Kim Biến Đồ Thảo',
            chapter: 'Chương 682',
            time: 'một phút trước',
        },
        {
            genre: 'Đô Thị',
            title: 'Tửu Kiếm Tiên: Thần Cấp Thợ Nấu Rượu, Say Trăm Thiên Môn Say Trăm Thiên Môn!',
            author: 'Tài Thán Khứ Na Liễu',
            chapter: 'Chương 306',
            time: '2 phút trước',
        },
        {
            genre: 'Tiên Hiệp',
            title: 'Ta Lấy Thân Thống Chứng Đạo Trường Sinh',
            author: 'Sóc Thỉ Vũ',
            chapter: 'Chương 46',
            time: '3 phút trước',
        },
        {
            genre: 'Huyền Huyễn',
            title: 'Đầu Tư Thiên Kiều Trăm Năm, Nữ Đế Xưng Ta Là Sư',
            author: 'Trần Cơ Trần',
            chapter: 'Chương 113',
            time: '3 phút trước',
        },
        {
            genre: 'Huyền Huyễn',
            title: 'Thăng Quan Liên Mạnh Lên, Ta Trực Tiếp Vô Địch!',
            author: 'Tường Thẳng Bình Dương',
            chapter: 'Chương 81',
            time: '4 phút trước',
        },
        {
            genre: 'Huyền Huyễn',
            title: 'Trọng Sinh Chi Ta Là Thế Giới Thiên Đạo',
            author: 'Công Đại Mộc',
            chapter: 'Chương 71',
            time: '4 phút trước',
        },
        {
            genre: 'Huyền Huyễn',
            title: 'Giai Đoạn Trước Ta Rắc Rưới, Hậu Kỳ Ta Vô Địch',
            author: 'Vô Thủy Vô Danh',
            chapter: 'Chương 548',
            time: '4 phút trước',
        },
        {
            genre: 'Đồng Nhân',
            title: 'Giới Ninja Đại Chiến Quá Nguy Hiểm, Chạy Trốn Đi Làm Hải Tặc A',
            author: 'Cẩn Phòng Giả 1',
            chapter: 'Chương 605',
            time: '4 phút trước',
        },
    ];

    return (
        <div className={styles.newChapters}>
            <div className={styles.title}>
                VỪA LÊN CHƯƠNG <span className={styles.viewMore}>»</span>
            </div>
            <ul className={styles.chapterList}>
                {chapters.map((chapter, index) => (
                    <li key={index} className={styles.chapterItem}>
                        <span className={styles.genre}>{chapter.genre}</span>
                        <span className={styles.chapterTitle}>
                            {chapter.title}
                        </span>
                        <span className={styles.author}>{chapter.author}</span>
                        <span className={styles.chapterNumber}>
                            {chapter.chapter}
                        </span>
                        <span className={styles.time}>{chapter.time}</span>
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default NewChapters;
