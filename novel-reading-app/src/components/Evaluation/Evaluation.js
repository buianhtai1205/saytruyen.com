import React, { useState, useEffect, useRef } from 'react';
import clsx from 'clsx';
import styles from './Evaluation.module.scss';

const initialEvaluations = [
    {
        id: 1,
        user: 'LSP2046',
        title: 'Ngày Mai Truyền Kỳ',
        rating: 5,
        content: 'Nhân vật: ko thánh mẫu, ko lạm sát nhưng cũng nếu cần giết là giết, có tinh thần trượng nghĩa cao\nCốt truyện: main đi theo hướng toàn dân tu tiên, cốt truyện xoay quanh việc nhờ hệ thống tạp giao giúp bản thân mình giàu lên, sau đó khiên toàn bộ người bình thường giàu lên theo\nThế giới: khá hay, bối cảnh tiên đạo nho đạo sụp đổ, hiện giờ hệ thống tu luyện theo hướng ngộ đạo. tức là 3600 nghề ai cũng thành tiên được.\nhê thống tu luyện kiểu 3600 nghề, nghề nào cũng thành tiên, trong đó nghề nông dân khổ chít chít ko được ai coi trọng cho đến khi main xuất hiện.',
        time: '9 giờ trước',
        likes: 0,
    },
    {
        id: 2,
        user: 'Võ Thoái Chân Nhân',
        title: 'Hokage: Tà Ở Konoha',
        rating: 5,
        content: 'Nhân vật: thông minh\nCốt truyện: khá logic\nThế giới: chủ yếu là ở mỹ. thỉnh thoảng sang tq du lịch\nnội dung truyện ok, ko dạng háng quá. hiếm lắm mới thấy một bộ về bóng rổ mà viết ổn như thế này.main một vợ (megan fox). 2 năm đầu ở laker ôm chân o’neal với kobe về sau sang suns làm lão đại. có tham gia vào ngành giải trí như đóng phim với ca nhạc (chủ yếu là nhờ copy các ý tưởng).chơi ở vị trí hậu vệ dẫn bóng. kết bạn với khá nhiều dv và ca sĩ nổi tiếng',
        time: '16 giờ trước',
        likes: 0,
    },
    {
        id: 3,
        user: 'Fap Fap Đại Sư',
        title: 'Ta Muốn Tu Tiên, Ta Không Muốn Làm Ông Trùm Truyền Thống Giải Trí',
        rating: 5,
        content: 'Nhân vật: cá ướp muối đúng nghĩa( k có gì thúc vào đít thì kbh làm), iq eq ở mức ổn, chủ yếu là khí vận chi tử nên chả cẩn động não làm gì\nCốt truyện: truyện viết chủ yếu là chuyện main đi nhặt cơ duyên với các trận chiến lq đến( từ các lãnh chúa với nhau đến các vương quốc rồi giới diện\nThế giới: bố cục khá logic, main ở vô thượng thế giới( k nhớ rõ tên lắm) nên đi đánh giới khác là chủ yếu, hệ thống tu luyện khá ổn( main tu kỵ sĩ ngoài ra còn có pháp sư ở thế giới chính nữa, theo văn tác thì còn có các thế giới khoa học, tu tiên các kiểu). \nTruyện phù hợp với ai thích đọc giải trí, ít động não, thử cảm giác là khí vận chi tử. Xây dựng nhân vật phụ ổn( mình thấy ấn tượng với kael-người đồng hành với main, như bảo mẫu main vậy).\nAi muốn đọc thể loại xây dựng lãnh địa thì xem xét khi đọc truyện này vì main nó k quan tâm đến pt lãnh địa lắm, chủ yếu vứt cho mấy thằng đệ làm. \nMình chấm 7/10',
        time: '1 giờ trước',
        likes: 0,
    },
    {
        id: 4,
        user: '6666t',
        title: 'Mắng Ai Thực Lực Phái Đâu',
        rating: 4,
        content: 'hậu cung giải trí văn con tác này trước giờ vẫn luôn là thể loại này (đây là bộ thứ ba ta đọc con tác này) bộ trước hoàn thành với bộ này chưa hoàn thành toàn vào top 5 qidi bên trên nó toàn là 1-1 (nhiều nữ đọc thì phải) so với bộ trước thì bộ này nhiều điểm sảng khoái hơn ở chỗ tula tràng ( bộ trước sảng điểm nhiều hơn ở tác phẩm, đạo từ nhạc đến phim) bộ này nhạc ít đi hẳn. Giải trí văn đi chuyện cũng như vậy nhưng phong cách con tác này hành văn bên bển nhiều người kiểm nhiệm rồi (luôn vào top) thêm cái bộ này tula tràng khá vui nhân vật phụ ra sức đọc có kình. Đợi mãi thấy 700 nhảy hố ngay',
        time: '2 giờ trước',
        likes: 0,
    },
];

function Evaluation() {
    const [evaluations, setEvaluations] = useState(initialEvaluations);
    const [expandedId, setExpandedId] = useState(null); // Track expanded evaluation
    const contentRefs = useRef([]);
    const [showReadMore, setShowReadMore] = useState(Array(initialEvaluations.length).fill(false)); // Track overflow

    const handleLike = (id) => {
        setEvaluations((prev) =>
            prev.map((evaluation) =>
                evaluation.id === id
                    ? { ...evaluation, likes: evaluation.likes + 1 }
                    : evaluation
            )
        );
    };

    const toggleExpand = (id) => {
        setExpandedId(expandedId === id ? null : id); // Toggle expanded state
    };

    useEffect(() => {
        evaluations.forEach((evaluation, index) => {
            const contentElement = contentRefs.current[index];
            console.log(contentElement);
            if (contentElement) {
                const lineHeight = parseFloat(getComputedStyle(contentElement).lineHeight);
                const maxHeight = lineHeight * 10; // 10 lines
                const isOverflowing = contentElement.scrollHeight > maxHeight;

                console.log(isOverflowing);
                
                setShowReadMore(prev => {
                    const newShowReadMore = [...prev];
                    newShowReadMore[index] = isOverflowing;
                    return newShowReadMore;
                });
            }
        });
    }, [evaluations]);

    console.log(showReadMore);


    return (
        <div className={clsx(styles.evaluation)}>
            <h2 className={clsx(styles.title)}>ĐÁNH GIÁ MỚI</h2>
            <div className={clsx(styles.evaluationList)}>
                {evaluations.map((evaluation, index) => {
                    const isExpanded = expandedId === evaluation.id;

                    return (
                        <div key={evaluation.id} className={clsx(styles.evaluationItem)}>
                            <div className={clsx(styles.evaluationTitleContainer)}>
                                <div className={clsx(styles.evaluationTitle)}>
                                    <span className={clsx(styles.userName)}>{evaluation.user}</span>
                                    <span className={clsx(styles.evaluationTitleSeparator)}> đã đánh giá </span>
                                    <span className={clsx(styles.title)}>{evaluation.title}</span>
                                </div>
                                <span className={clsx(styles.rating)}>⭐ {evaluation.rating}</span>
                            </div>
                            <p
                                ref={(el) => (contentRefs.current[index] = el)}
                                className={clsx(styles.evaluationContent, { [styles.expanded]: isExpanded })}
                            >
                                {evaluation.content}
                            </p>
                            {/* {showReadMore[index] && !isExpanded && (
                                <button onClick={() => toggleExpand(evaluation.id)} className={clsx(styles.readMoreButton)}>
                                    Đọc tiếp
                                </button>
                            )} */}
                            <div className={clsx(styles.likeSection)}>
                                <button onClick={() => handleLike(evaluation.id)} className={clsx(styles.likeButton)}>
                                    👍 {evaluation.likes}
                                </button>
                                <span className={clsx(styles.time)}>{evaluation.time}</span>
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}

export default Evaluation;