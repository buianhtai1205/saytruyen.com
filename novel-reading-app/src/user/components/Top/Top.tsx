import React from 'react';
import styles from './Top.module.scss';
import rankIndex1 from '../../assets/images/rank-index-1.webp';
import rankIndex2 from '../../assets/images/rank-index-2.webp';
import rankIndex3 from '../../assets/images/rank-index-3.webp';

const rankImages = [rankIndex1, rankIndex2, rankIndex3];

// Định nghĩa kiểu cho book
interface Book {
    id: number;
    title: string;
    readers: string;
    author?: string;
    genre?: string;
    coverImage?: string;
}

// Định nghĩa kiểu cho props của TopRank
interface TopRankProps {
    title: string;
    books: Book[];
}

const TopRank: React.FC<TopRankProps> = ({ title, books }) => (
    <div className={styles.topRank}>
        <h2 className={styles.title}>
            {title} <span className={styles.viewMore}>»</span>
        </h2>
        <ul className={styles.bookList}>
            {books.map((book, index) => (
                <li
                    key={book.id}
                    className={`${styles.bookItem} ${index < 3 ? styles.topBook : ''}`}
                >
                    <span className={styles.rank}>
                        {index < 3 ? (
                            <img
                                src={rankImages[index]}
                                alt={`Rank ${index + 1}`}
                                className={styles.rankImage}
                            />
                        ) : (
                            index + 1
                        )}
                    </span>
                    {index === 0 ? (
                        <>
                            <div className={styles.bookInfo}>
                                <h3 className={styles.bookTitle}>
                                    {book.title}
                                </h3>
                                <p className={styles.readers}>{book.readers}</p>
                                {book.author && (
                                    <p className={styles.author}>
                                        {book.author}
                                    </p>
                                )}
                                {book.genre && (
                                    <span className={styles.genre}>
                                        {book.genre}
                                    </span>
                                )}
                            </div>
                            {book.coverImage && (
                                <img
                                    src={book.coverImage}
                                    alt={book.title}
                                    className={styles.coverImage}
                                />
                            )}
                        </>
                    ) : (
                        <>
                            <span className={styles.bookTitle}>
                                {book.title}
                            </span>
                            <span className={styles.readers}>
                                {book.readers}
                            </span>
                        </>
                    )}
                </li>
            ))}
        </ul>
    </div>
);

const Top = () => {
    const timeRankBooks: Book[] = [
        {
            id: 1,
            title: 'Vì Phu Chi Muốn Lẳng Lặng Nhìn Xem Người Trưởng Sinh',
            readers: '161 người đang đọc',
            author: 'Lý Hồng Thiên',
            genre: 'Huyền Huyễn',
            coverImage:
                'https://static.cdnno.com/poster/vi-phu-chi-muon-lang-lang-nhin-xem-nguoi-truong-sinh/150.jpg?1724891265',
        },
        {
            id: 2,
            title: 'Tu Luyện Giản Lược Hóa Công Pháp Bắt Đầu',
            readers: '159',
        },
        { id: 3, title: 'Vạn Cổ Thần Đế', readers: '120' },
        {
            id: 4,
            title: 'Cầu Thả Tại Nữ Ma Đầu Bên Người Vụng Trộm Tu Luyện',
            readers: '101',
        },
        {
            id: 5,
            title: 'Cầu Thả Thanh Thanh Nhân, Tiên Quan Triệu Ta Chăm Ngựa',
            readers: '99',
        },
        {
            id: 6,
            title: 'Toàn Dân Chuyển Chức: Từ Linh Pháp Sư! Ta Tức Là Thiên Tài',
            readers: '92',
        },
        { id: 7, title: 'Ta Có Một Thân Bị Động Kỹ', readers: '87' },
        { id: 8, title: 'Từ Hải Nhi Bắt Đầu Nhập Đạo', readers: '80' },
        {
            id: 9,
            title: 'Ta Tại Bệnh Viện Tâm Thần Học Trầm Thần',
            readers: '76',
        },
        {
            id: 10,
            title: 'Chúng Ta Còn Không Có Tốt Nghiệp, Bỏ Học Người Thành Chiến...',
            readers: '72',
        },
    ];

    const topRecommendedBooks: Book[] = [
        {
            id: 1,
            title: 'Cầu Thả Tại Nữ Ma Đầu Bên Người Vụng Trộm Tu Luyện',
            readers: '3079 tặng thưởng',
            author: 'Pha Lạt Đích Hồng Tiêu',
            genre: 'Tiên Hiệp',
            coverImage:
                'https://static.cdnno.com/poster/bat-dau-nu-ma-dau-phu-ta/300.jpg?1679461039',
        },
        { id: 2, title: 'Từ Hải Nhi Bắt Đầu Nhập Đạo', readers: '2685' },
        { id: 3, title: 'Xích Tâm Tuần Thiên', readers: '2031' },
        {
            id: 4,
            title: 'Cầu Thả Thanh Thanh Nhân, Tiên Quan Triệu Ta Chăm Ngựa',
            readers: '1841',
        },
        { id: 5, title: 'Quang Âm Chi Ngoại', readers: '1515' },
        { id: 6, title: 'Ta Có Một Thân Bị Động Kỹ', readers: '1417' },
        { id: 7, title: 'Trận Hồi Trưởng Sinh', readers: '1366' },
        { id: 8, title: 'Ai Bảo Hắn Tu Tiên!', readers: '1169' },
        { id: 9, title: 'Luận Hồi Nhạc Viên', readers: '905' },
        { id: 10, title: 'Nhân Đạo Đại Thành', readers: '647' },
    ];

    return (
        <div className={styles.topContainer}>
            <TopRank title="THỜI GIAN THỰC" books={timeRankBooks} />
            <TopRank title="TOP ĐỀ CỬ" books={topRecommendedBooks} />
        </div>
    );
};

export default Top;
