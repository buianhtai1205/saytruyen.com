import React from 'react';
import Banner from '../../components/Banner/Banner';
import StoryInfo from '../../components/StoryInfo/StoryInfo';
import RelatedStory from '../../components/RelatedStory/RelatedStory.tsx';
import ListChapter from '../../components/ListChapter/ListChapter.tsx';
import RatingStory from '../../components/RatingStory/RatingStory.tsx';
import StoryDescription from '../../components/StoryDescription/StoryDescription.tsx';

const storyData = {
    title: 'Bắt Đầu Kim Phong Tế Vũ Lâu Chủ, Một Đao Kinh Thiên Hạ',
    author: 'Tháng Đầu Yên Dân',
    stats: {
        chaptersPerWeek: 20,
        reads: 2330562,
        recommendations: 5496,
        saved: 2676,
    },
    latestChapter: [
        {
            title: 'Chương 1001: Các người có hay không nghĩ tới, mối ta là hoàng tước',
            timeAgo: '27 phút trước',
        },
        {
            title: 'Chương 1002: Các người có hay không nghĩ tới, mối ta là hoàng tước',
            timeAgo: '27 phút trước',
        },
        {
            title: 'Chương 1003: Các người có hay không nghĩ tới, mối ta là hoàng tước',
            timeAgo: '27 phút trước',
        },
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
            <ListChapter />
            <Banner
                imageUrl="https://static.cdnno.com/storage/topbox/28b185eb7cd5474d4cad2723edc3ff45.webp"
                linkUrl="/story-detail"
                altText="Lam Ruong Duc Yeu"
            />
            <StoryDescription />
            <RelatedStory />
            <Banner
                imageUrl="https://static.cdnno.com/storage/topbox/24bb76ef34c9a2962727fae9f62ae4de.webp"
                linkUrl="/story-detail"
                altText="Lam Ruong Duc Yeu"
            />
            <RatingStory />
        </>
    );
};

export default StoryDetail;
