import React, { useState } from 'react';
import Slider from 'react-slick';
import { useNavigate } from 'react-router-dom';
import clsx from 'clsx';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import styles from './NewCompletedStory.module.scss';

const stories = [
    {
        id: 1,
        title: 'Thực Vật Linh Chủ: Bắt Đầu Một Góc Thời Gian Thần Thụ!',
        imageUrl:
            'https://static.cdnno.com/poster/thuc-vat-linh-chu-bat-dau-gap-tram-lan-thoi-gian-gia-toc/300.jpg?1699016768',
    },
    {
        id: 2,
        title: 'Tôi Có Kỹ Năng Của Cristiano Ronaldo ! Siuuuuu',
        imageUrl:
            'https://static.cdnno.com/poster/toi-co-ky-nang-cua-cristiano-ronaldo-siuuuuu/300.jpg?1687176733',
    },
    {
        id: 3,
        title: 'Tại Hạ, Elden Kiếm Thánh',
        imageUrl:
            'https://static.cdnno.com/poster/tai-ha-elden-kiem-thanh/300.jpg?1725020030',
    },
    {
        id: 4,
        title: 'Phản Phái: Sư Muội Ta Tất Cả Đều Là Hắc Hóa Nữ Đế',
        imageUrl:
            'https://static.cdnno.com/poster/phan-phai-su-muoi-ta-tat-ca-deu-la-hac-hoa-nu-de/300.jpg?1670137237',
    },
    {
        id: 5,
        title: 'Xuyên Thư Liền Từ Hôn, Ta Có Thể Nghe Tới Nữ Chính Tiếng Lòng',
        imageUrl:
            'https://static.cdnno.com/poster/xuyen-thu-lien-tu-hon-ta-co-the-nghe-toi-nu-chinh-tieng-long/300.jpg?1724582818',
    },
    {
        id: 6,
        title: 'Ẩn Thế Ma Tôn, Nữ Đế Mang Em Bé Tới Cửa Cầu Phụ Trách',
        imageUrl:
            'https://static.cdnno.com/poster/an-the-ma-ton-nu-de-mang-em-be-toi-cua-cau-phu-trach/300.jpg?1699587973',
    },
    {
        id: 7,
        title: 'Theo Hải Tặc Bắt Đầu Toàn Trí Toàn Năng',
        imageUrl:
            'https://static.cdnno.com/poster/theo-hai-tac-bat-dau-toan-tri-toan-nang/300.jpg?1725083504',
    },
    {
        id: 8,
        title: 'Nữ Nhi Đại Náo Tu Tiên Giới, Ta Tiên Đế Thân Phận Bị Lộ Ra',
        imageUrl:
            'https://static.cdnno.com/poster/thuc-vat-linh-chu-bat-dau-gap-tram-lan-thoi-gian-gia-toc/300.jpg?1699016768',
    },
    {
        id: 9,
        title: 'Nguyên Lai Bảo Tàng Nữ Hài Đợi Ta Mười Bảy Năm',
        imageUrl:
            'https://static.cdnno.com/poster/nguyen-lai-bao-tang-nu-hai-doi-ta-muoi-bay-nam/300.jpg?1643558807',
    },
    {
        id: 10,
        title: 'Ta Có Thể Đi Vào Chúng Sinh Kiếp Trước',
        imageUrl:
            'https://static.cdnno.com/poster/ta-co-the-di-vao-chung-sinh-kiep-truoc/300.jpg?1674099883',
    },
];

function NewCompletedStory() {
    const [isDragging, setIsDragging] = useState(false);
    const navigate = useNavigate();

    const settings = {
        dots: false,
        infinite: false,
        speed: 500,
        slidesToShow: 5.5,
        slidesToScroll: 5,
        autoplay: false,
        arrows: true,
        beforeChange: () => setIsDragging(true),
        afterChange: () => setIsDragging(false),
        responsive: [
            {
                breakpoint: 1024,
                settings: {
                    slidesToShow: 5.5,
                    slidesToScroll: 5,
                },
            },
            {
                breakpoint: 768,
                settings: {
                    slidesToShow: 3.5,
                    slidesToScroll: 3,
                },
            },
            {
                breakpoint: 480,
                settings: {
                    slidesToShow: 2.5,
                    slidesToScroll: 2,
                },
            },
        ],
    };

    const handleClick = (
        e: React.MouseEvent<HTMLDivElement>,
        storyId: number
    ) => {
        if (isDragging) {
            e.preventDefault();
        } else {
            navigate(`/story/${storyId}`);
        }
    };

    return (
        <div className={clsx(styles.newCompletedStory)}>
            <h2 className={clsx(styles.title)}>MỚI HOÀN THÀNH</h2>
            <Slider {...settings}>
                {stories.map((story) => (
                    <div key={story.id} className={clsx(styles.storyItem)}>
                        <div
                            onClick={(e) => handleClick(e, story.id)}
                            className={clsx(styles.storyLink)}
                        >
                            <img
                                src={story.imageUrl}
                                alt={story.title}
                                className={clsx(styles.storyImage)}
                            />
                            <p className={clsx(styles.storyTitle)}>
                                {story.title}
                            </p>
                        </div>
                    </div>
                ))}
            </Slider>
        </div>
    );
}

export default NewCompletedStory;
