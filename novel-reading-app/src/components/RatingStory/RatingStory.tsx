import React from 'react';
import { useState } from 'react';
import clsx from 'clsx';
import styles from './RatingStory.module.scss';

interface Review {
    id: number;
    user: {
        name: string;
        avatar: string;
        rating?: number;
        chaptersRead?: number;
    };
    content: string;
    likes: number;
    replies: number;
    timestamp: string;
    chapter?: string;
}

interface Fan {
    id: number;
    name: string;
    avatar: string;
    points: number;
}

const reviews: Review[] = [
    {
        id: 1,
        user: {
            name: 'MarchiHz',
            avatar: 'https://static.cdnno.com/static/fans/da-chu.png',
            rating: 5,
            chaptersRead: 225,
        },
        content: `Sáng văn, vô địch, nhẹ nhàng.
  Truyện như ôn lại những nhân vật, những câu chuyện của Kim Dung, Cổ Long... Cùng với đó là những bình luận, nhận xét về các nhân vật ấy. Có thể là của tác giả, của mạng, của thế hệ người trẻ bây h (Thông qua những bình luận của người trong quán rượu)
  Nói thật, bộ này cảm giác con tác làm để tri ân thời đại của kiếm hiệp cổ điển, nhất là chữ "hiệp" đang ngày càng mất dần trong văn học hiện nay với sự xh quá nhiều các thể loại mới của văn học mạng.
  Khuyến khích những ai mong muốn quay lại thời mà đọc cuốn sách in trên giấy vang của Cổ Long, của Kim Dung. Những Lý Tầm Hoan, Dương Quá, Lệnh Hồ Xung, Nhạc Bất Quần, ... sẽ hiện lên một cách mới hơn, nhiệt huyết hơn và sẽ thú vị hơn`,
        likes: 17,
        replies: 4,
        timestamp: '17 ngày trước',
    },
];

const fans: Fan[] = [
    {
        id: 1,
        name: 'IwAZZ77826',
        avatar: 'https://static.cdnno.com/static/fans/hoc-do.png',
        points: 265000,
    },
    {
        id: 2,
        name: 'Kỳ Sinh Trùng',
        avatar: 'https://static.cdnno.com/static/fans/chap-su.png',
        points: 75000,
    },
    // Add more fans as needed...
];

type TabType = 'rating' | 'discussion' | 'fans';

export default function NovelTabs() {
    const [activeTab, setActiveTab] = useState<TabType>('rating');
    const [rating, setRating] = useState(5);
    const [ratingOnly, setRatingOnly] = useState(false);
    const [discussionText, setDiscussionText] = useState('');

    return (
        <div className={clsx(styles.container)}>
            {/* Tab Headers */}
            <div className={clsx(styles.tabs)}>
                <button
                    className={`${clsx(styles.tab)} ${activeTab === 'rating' ? styles.active : ''}`}
                    onClick={() => setActiveTab('rating')}
                >
                    ĐÁNH GIÁ
                    <span className={clsx(styles.count)}>17</span>
                </button>
                <button
                    className={`${clsx(styles.tab)} ${activeTab === 'discussion' ? styles.active : ''}`}
                    onClick={() => setActiveTab('discussion')}
                >
                    THẢO LUẬN
                    <span className={clsx(styles.count)}>291</span>
                </button>
                <button
                    className={`${clsx(styles.tab)} ${activeTab === 'fans' ? styles.active : ''}`}
                    onClick={() => setActiveTab('fans')}
                >
                    HÂM MỘ
                    <span className={clsx(styles.countRed)}>3</span>
                </button>
            </div>

            {/* Rating Tab Content */}
            {activeTab === 'rating' && (
                <div className={clsx(styles.ratingContent)}>
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
                                onChange={(e) =>
                                    setRatingOnly(e.target.checked)
                                }
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

                    <div className={clsx(styles.guidelines)}>
                        <p>
                            - Từ phiên bản mới các bài đánh giá có nội dung sẽ
                            được các BTV duyệt đọc trước khi được hiển thị.
                        </p>
                        <p>
                            - Nếu bạn chỉ muốn chấm điểm cho truyện, không muốn
                            viết đánh giá, hãy tích vào "Tôi chỉ muốn chấm
                            điểm".
                        </p>
                        <p>
                            - Vui lòng đọc kỹ{' '}
                            <a href="/#">Điều khoản dịch vụ</a> và{' '}
                            <a href="/#">Hướng dẫn sử dụng</a> trước khi viết
                            đánh giá.
                        </p>
                        <p>
                            - Các đánh giá trước ở phiên bản cũ có nội dung quá
                            ngắn và không có tương tác mặc định sẽ không được
                            hiển thị, bạn có thể xem nó bằng cách tích vào "Hiện
                            tất cả đánh giá".
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
                            <div
                                key={review.id}
                                className={clsx(styles.reviewCard)}
                            >
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
                                        <div
                                            className={clsx(styles.ratingInfo)}
                                        >
                                            <span
                                                className={clsx(styles.stars)}
                                            >
                                                ★ {review.user.rating}
                                            </span>
                                            <span
                                                className={clsx(
                                                    styles.chapters
                                                )}
                                            >
                                                {review.user.chaptersRead}{' '}
                                                chương
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
                                    <button
                                        className={clsx(styles.actionButton)}
                                    >
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
                                    <button
                                        className={clsx(styles.actionButton)}
                                    >
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
            )}

            {/* Discussion Tab Content */}
            {activeTab === 'discussion' && (
                <div className={clsx(styles.discussionContent)}>
                    <textarea
                        className={clsx(styles.discussionInput)}
                        placeholder="Thảo luận ..."
                        value={discussionText}
                        onChange={(e) => setDiscussionText(e.target.value)}
                    />

                    <div className={clsx(styles.discussionControls)}>
                        <select className={clsx(styles.sortSelect)}>
                            <option>Mới nhất</option>
                        </select>
                        <span className={clsx(styles.discussionCount)}>
                            291 thảo luận
                        </span>
                        <button className={clsx(styles.submitButton)}>
                            GỬI
                        </button>
                    </div>

                    <div className={clsx(styles.discussionList)}>
                        {reviews.map((review) => (
                            <div
                                key={review.id}
                                className={clsx(styles.discussionCard)}
                            >
                                <div className={clsx(styles.discussionHeader)}>
                                    <img
                                        src={review.user.avatar}
                                        alt=""
                                        className={clsx(styles.avatar)}
                                    />
                                    <div className={clsx(styles.userInfo)}>
                                        <span className={clsx(styles.userName)}>
                                            {review.user.name}
                                        </span>
                                        <span
                                            className={clsx(styles.timestamp)}
                                        >
                                            {review.timestamp}
                                        </span>
                                    </div>
                                    <button className={clsx(styles.moreButton)}>
                                        •••
                                    </button>
                                </div>
                                <div className={clsx(styles.discussionBody)}>
                                    <p className={clsx(styles.content)}>
                                        {review.content}
                                    </p>
                                </div>
                                <div className={clsx(styles.discussionFooter)}>
                                    <button
                                        className={clsx(styles.actionButton)}
                                    >
                                        👍 {review.likes} Thích
                                    </button>
                                    <button
                                        className={clsx(styles.actionButton)}
                                    >
                                        💬 {review.replies} Trả lời
                                    </button>
                                    {review.chapter && (
                                        <span className={clsx(styles.chapter)}>
                                            {review.chapter}
                                        </span>
                                    )}
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            )}

            {/* Fans Tab Content */}
            {activeTab === 'fans' && (
                <div className={clsx(styles.fansContent)}>
                    <div className={clsx(styles.fansGrid)}>
                        {fans.map((fan) => (
                            <div key={fan.id} className={clsx(styles.fanCard)}>
                                <img
                                    src={fan.avatar}
                                    alt=""
                                    className={clsx(styles.avatar)}
                                />
                                <div className={clsx(styles.fanInfo)}>
                                    <span className={clsx(styles.fanName)}>
                                        {fan.name}
                                    </span>
                                    <span className={clsx(styles.fanPoints)}>
                                        {fan.points.toLocaleString()}
                                    </span>
                                </div>
                            </div>
                        ))}
                    </div>
                    <button className={clsx(styles.viewAllButton)}>
                        Xem hết 55 người hâm mộ
                    </button>
                    <div className={clsx(styles.fanGuidelines)}>
                        <p>
                            - Khi bạn mở khóa, để cử hay tặng quà cho truyện,
                            bạn trở thành người hâm mộ của truyện.
                        </p>
                        <p>
                            - Tiêu vài 1 🔑 = 1 điểm hâm mộ (mở khóa, tặng quà)
                        </p>
                        <p>
                            - Dùng ⭐ mở khóa được số điểm bằng số 🔑 cần để mở
                            khóa
                        </p>
                        <p>- Để cử truyện bằng 💳 = 1000 điểm hâm mộ</p>
                    </div>
                </div>
            )}
        </div>
    );
}
