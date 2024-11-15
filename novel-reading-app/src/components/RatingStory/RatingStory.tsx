import React from 'react';
import { useState } from 'react';
import clsx from 'clsx';
import styles from './RatingStory.module.scss';

interface Review {
    id: number;
    user: {
        name: string;
        avatar: string;
    };
    rating: number;
    chaptersRead: number;
    content: string;
    likes: number;
    replies: number;
    timestamp: string;
}

const reviews: Review[] = [
    {
        id: 1,
        user: {
            name: 'Từ Vương Tử',
            avatar: 'https://static.cdnno.com/user/49944c87a678f2678a5233ed8bc89711/200.jpg?1723179338',
        },
        rating: 2.3,
        chaptersRead: 40,
        content:
            'buff main hơi quá, vừa vào đã có phòng ngự vô địch, được mười mấy chap thêm đi chuyển siêu bá, đạt được nội công đỉnh lưu được mấy hôm tác buff luôn vô hạn mana. chưa được 100 chap đã ntn được 100 chap chắc vô địch luôn quá\nkiểu buff khỏe như này toàn main ko ra gì, atsm, não tàn, trang b... sức mạnh tăng quá nhanh khiến tâm lý không theo kịp\ntuyến tình cảm rất vớ vẩn rất gượng sư đồ luyện lại nv nữ thích main phần nhiều là main đẹp trai còn lại do main cứu nó mấy lần',
        likes: 17,
        replies: 4,
        timestamp: '7 tháng trước',
    },
];

export default function RatingStory() {
    const [rating, setRating] = useState(5);
    const [ratingOnly, setRatingOnly] = useState(false);

    return (
        <div className={clsx(styles.container)}>
            {/* Rating Tabs */}
            <div className={clsx(styles.tabs)}>
                <button
                    className={`${clsx(styles.tab)} ${clsx(styles.active)}`}
                >
                    ĐÁNH GIÁ
                    <span className={clsx(styles.count)}>69</span>
                </button>
                <button className={clsx(styles.tab)}>
                    THẢO LUẬN
                    <span className={clsx(styles.count)}>858</span>
                </button>
                <button className={clsx(styles.tab)}>
                    HÂM MỘ
                    <span className={clsx(styles.countRed)}>3</span>
                </button>
            </div>

            {/* Rating Form */}
            <div className={clsx(styles.ratingForm)}>
                <div className={clsx(styles.ratingHeader)}>
                    <span>Chấm điểm nội dung truyện: </span>
                    <span className={clsx(styles.ratingValue)}>
                        {rating} điểm
                    </span>
                </div>

                <input
                    type="range"
                    min="0"
                    max="5"
                    step="0.1"
                    value={rating}
                    onChange={(e) => setRating(Number(e.target.value))}
                    className={clsx(styles.ratingSlider)}
                />

                <label className={clsx(styles.ratingOnly)}>
                    <input
                        type="checkbox"
                        checked={ratingOnly}
                        onChange={(e) => setRatingOnly(e.target.checked)}
                    />
                    Tôi chỉ muốn chấm điểm (không viết đánh giá)
                </label>

                {!ratingOnly && (
                    <div className={clsx(styles.formFields)}>
                        <input
                            type="text"
                            placeholder="Nhân vật chính như nào? (nhiệt huyết?, vô sỉ?, thông minh? ...)"
                            className={clsx(styles.input)}
                        />
                        <input
                            type="text"
                            placeholder="Cốt truyện như nào? (logic?, sáng vân?, bố cục nhiều lớp?, quay xe liên tục? ...)"
                            className={clsx(styles.input)}
                        />
                        <input
                            type="text"
                            placeholder="Bố cục thế giới như nào? (lớn hay nhỏ?, một thế giới?, nhiều thế giới?, nhiều tầng? ...)"
                            className={clsx(styles.input)}
                        />
                        <textarea
                            placeholder="Nội dung bài đánh giá (ít nhất 100 từ)..."
                            className={clsx(styles.textarea)}
                            rows={4}
                        />
                    </div>
                )}

                <button className={clsx(styles.submitButton)}>
                    GỬI ĐÁNH GIÁ
                </button>
            </div>

            {/* Guidelines */}
            <div className={clsx(styles.guidelines)}>
                <p>
                    - Từ phiên bản mới các bài đánh giá có nội dung sẽ được các
                    BTV duyệt đọc trước khi được hiển thị.
                </p>
                <p>
                    - Nếu bạn chỉ muốn chấm điểm cho truyện, không muốn viết
                    đánh giá, hãy tích vào "Tôi chỉ muốn chấm điểm".
                </p>
                <p>
                    - Vui lòng đọc kỹ <a href="#">Điều khoản dịch vụ</a> và{' '}
                    <a href="#">Hướng dẫn sử dụng</a> trước khi viết đánh giá.
                </p>
                <p>
                    - Các đánh giá trước ở phiên bản cũ có nội dung quá ngắn và
                    không có tương tác mặc định sẽ không được hiển thị, bạn có
                    thể xem nó bằng cách tích vào "Hiện tất cả đánh giá".
                </p>
            </div>

            {/* Reviews List */}
            <div className={clsx(styles.reviewsList)}>
                <div className={clsx(styles.reviewsHeader)}>
                    <label className={clsx(styles.showAll)}>
                        <input type="checkbox" />
                        Hiện tất cả
                    </label>
                    <span>69 đánh giá</span>
                    <select className={clsx(styles.sort)}>
                        <option>Lượt thích</option>
                    </select>
                </div>

                {reviews.map((review) => (
                    <div key={review.id} className={clsx(styles.reviewCard)}>
                        <div className={clsx(styles.reviewHeader)}>
                            <img
                                src={review.user.avatar}
                                alt={review.user.name}
                                className={clsx(styles.avatar)}
                            />
                            <div className={clsx(styles.reviewInfo)}>
                                <div className={clsx(styles.userName)}>
                                    {review.user.name}
                                </div>
                                <div className={clsx(styles.ratingInfo)}>
                                    <span className={clsx(styles.stars)}>
                                        ★ {review.rating}
                                    </span>
                                    <span className={clsx(styles.chapters)}>
                                        {review.chaptersRead} chương
                                    </span>
                                </div>
                            </div>
                            <button className={clsx(styles.moreButton)}>
                                •••
                            </button>
                        </div>
                        <div className={clsx(styles.reviewContent)}>
                            {review.content}
                        </div>
                        <div className={clsx(styles.reviewFooter)}>
                            <button className={clsx(styles.actionButton)}>
                                <svg
                                    viewBox="0 0 24 24"
                                    className={clsx(styles.icon)}
                                >
                                    <path
                                        d="M14 9V5a3 3 0 0 0-3-3l-4 9v11h11.28a2 2 0 0 0 2-1.7l1.38-9a2 2 0 0 0-2-2.3zM7 22H4a2 2 0 0 1-2-2v-7a2 2 0 0 1 2-2h3"
                                        fill="none"
                                        stroke="currentColor"
                                        strokeWidth="2"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                    />
                                </svg>
                                {review.likes} Thích
                            </button>
                            <button className={clsx(styles.actionButton)}>
                                <svg
                                    viewBox="0 0 24 24"
                                    className={clsx(styles.icon)}
                                >
                                    <path
                                        d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2z"
                                        fill="none"
                                        stroke="currentColor"
                                        strokeWidth="2"
                                        strokeLinecap="round"
                                        strokeLinejoin="round"
                                    />
                                </svg>
                                {review.replies} Trả lời
                            </button>
                            <span className={clsx(styles.timestamp)}>
                                {review.timestamp}
                            </span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}
