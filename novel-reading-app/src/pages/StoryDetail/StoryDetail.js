import React from 'react';
import Banner from '../../components/Banner/Banner';
import StoryInfo from '../../components/StoryInfo/StoryInfo';

const storyData = {
    title: "Bắt Đầu Kim Phong Tế Vũ Lâu Chủ, Một Đao Kinh Thiên Hạ",
    author: "Tháng Đầu Yên Dân",
    stats: {
        chaptersPerWeek: 20,
        reads: 2330562,
        recommendations: 5496,
        saved: 2676,
    },
    latestChapter: [
        {
            title: "Chương 1001: Các người có hay không nghĩ tới, mối ta là hoàng tước",
            timeAgo: "27 phút trước",
        },
        {
            title: "Chương 1002: Các người có hay không nghĩ tới, mối ta là hoàng tước",
            timeAgo: "27 phút trước",
        },
        {
            title: "Chương 1003: Các người có hay không nghĩ tới, mối ta là hoàng tước",
            timeAgo: "27 phút trước",
        }
    ],
};

const StoryDetail = () => {

    return (
        <>
            <Banner
                imageUrl="https://static.cdnno.com/storage/topbox/1f84ac535622a55309d6da9fcc874397.webp"
                linkUrl="/story-detail"
                altText="Lam Ruong Duc Yeu"
            />
            <StoryInfo {...storyData} />
        </>
    );
};

export default StoryDetail;