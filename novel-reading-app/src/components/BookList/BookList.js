import React from 'react';
import clsx from 'clsx';
import styles from './BookList.module.scss';

const mockBooks = [
    {
        id: 1,
        title: 'Tron Lan Tan The, Mot Minh Thanh Tien',
        author: 'Ngon Quy Chinh Truyen',
        description:
            'Day la mot cai nat vun the gioi, nhan loai van minh tai nh an thu tai bien sau tinh cau ben tren keo dai hoi tan, ph...',
        genre: 'Tien Hiep',
        coverUrl:
            'https://static.cdnno.com/poster/tron-lan-tan-the-mot-minh-thanh-tien/300.jpg?1720868750',
    },
    {
        id: 2,
        title: 'Tinh Khong Chuc Nghiep Gia',
        author: 'Van Sao Cong',
        description:
            'Tinh khong lich nguyen nien, nhan loai tien vao thoi dai v u tru, tinh te di dan, phi thuyen vu tru, ngoai hanh tinh q...',
        genre: 'Huyen Huyen',
        coverUrl:
            'https://static.cdnno.com/poster/tinh-khong-chuc-nghiep-gia/300.jpg?1720839023',
    },
    {
        id: 3,
        title: 'Tinh Khong Cham Nghiep Gia',
        author: 'Bich Thao Luu Ly',
        description:
            'Nguoi sinh truong tai mot hanh tinh nho, trong mot vu tru rong lon, khong ngo lai la mot thien tai tu nhien...',
        genre: 'Huyen Huyen',
        coverUrl:
            'https://static.cdnno.com/poster/ta-tai-tu-tien-gioi-van-co-truong-thanh/300.jpg?1668162568',
    },
    {
        id: 4,
        title: 'Nhat Kiem Ba Thien',
        author: 'Vinh Da Tam Tu',
        description:
            'Ngan nam truoc, bat dau tu cuoc chien giua nhan loai va yeu quai, the gioi bi chia thanh ba mien...',
        genre: 'Xuyen Khong',
        coverUrl:
            'https://static.cdnno.com/poster/nhat-kiem-ba-thien/300.jpg?1724402661',
    },
    {
        id: 5,
        title: 'Tho Minh Chi Hoan',
        author: 'Ai Thien Thuy Duoc O Tac',
        description:
            'Thien mang chi hoan, than minh chi hoan, tho minh chi hoan. Tam hoan toan my, tao nen mot vi than...',
        genre: 'Tien Hiep',
        coverUrl:
            'https://static.cdnno.com/poster/tuc-menh-chi-hoan/300.jpg?1718101574',
    },
    {
        id: 6,
        title: 'Nhan Dao Dai Thanh',
        author: 'Mac Mac',
        description:
            'Trong the gioi nay, nhan dao moi la con duong chinh dao. Tren con duong nhan dao, ta se tro thanh vi dai...',
        genre: 'Kiem Hiep',
        coverUrl:
            'https://static.cdnno.com/poster/dai-dao-vo-cuc/300.jpg?1632478682',
    },
];

const BookList = () => (
    <div className={clsx(styles.bookList)}>
        <h2 className={clsx(styles.title)}>BTV DE CU</h2>
        <div className={clsx(styles.books)}>
            {mockBooks.map((book) => (
                <div key={book.id} className={clsx(styles.bookCard)}>
                    <img
                        src={book.coverUrl}
                        alt={book.title}
                        className={clsx(styles.bookImage)}
                    />
                    <div className={clsx(styles.bookInfo)}>
                        <h3 className={clsx(styles.bookTitle)}>{book.title}</h3>
                        <p className={clsx(styles.bookDescription)}>
                            {book.description}
                        </p>
                        <div className={clsx(styles.bookMeta)}>
                            <span className={clsx(styles.author)}>
                                {book.author}
                            </span>
                            <span className={clsx(styles.genreButton)}>
                                {book.genre}
                            </span>
                        </div>
                    </div>
                </div>
            ))}
        </div>
    </div>
);

export default BookList;
