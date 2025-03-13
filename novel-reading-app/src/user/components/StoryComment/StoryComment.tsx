import React, { useState } from 'react';
import clsx from 'clsx';
import styles from './StoryComment.module.scss';

const StoryComment: React.FC = () => {
    const [comments, setComments] = useState([
        {
            username: 'Phu123sis',
            time: '8 ngày trước',
            text: 'Với tình trạng còn lâu mới end nhé. Quả này vắt hết mấy trăm hộp sữa ông thọ mới hết...',
        },
    ]);

    const loadMoreComments = () => {
        const newComments = [
            {
                username: 'tiểu thỏ trong suốt',
                time: '8 ngày trước',
                text: 'vượt qua 1 tỷ thù hận thông báo? này mà là Hàn thỏ thì có việc vui =))',
            },
        ];
        setComments([...comments, ...newComments]);
    };

    return (
        <div className={clsx(styles.commentSection)}>
            <textarea
                className={clsx(styles.commentInput)}
                placeholder="Thảo luận ..."
            />
            <button className={clsx(styles.submitButton)}>Gửi</button>
            <div className={clsx(styles.commentList)}>
                {comments.map((comment, index) => (
                    <div key={index} className={clsx(styles.comment)}>
                        <div className={clsx(styles.commentHeader)}>
                            <span className={clsx(styles.username)}>
                                {comment.username}
                            </span>
                            <span className={clsx(styles.time)}>
                                {comment.time}
                            </span>
                        </div>
                        <div className={clsx(styles.commentBody)}>
                            {comment.text}
                        </div>
                        <div className={clsx(styles.commentFooter)}>
                            <button className={clsx(styles.likeButton)}>
                                0 Thích
                            </button>
                            <button className={clsx(styles.replyButton)}>
                                0 Trả lời
                            </button>
                        </div>
                    </div>
                ))}
            </div>
            <button
                className={clsx(styles.showMoreButton)}
                onClick={loadMoreComments}
            >
                XEM THÊM
            </button>
        </div>
    );
};

export default StoryComment;
