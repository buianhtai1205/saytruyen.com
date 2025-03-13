import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import _ from 'lodash';
import Banner from '@userComponents/Banner/Banner';
import StoryInfo from '@userComponents/StoryInfo/StoryInfo';
import RelatedStory from '@userComponents/RelatedStory/RelatedStory';
import ListChapter from '@userComponents/ListChapter/ListChapter';
import RatingStory from '@userComponents/RatingStory/RatingStory';
import StoryDescription from '@userComponents/StoryDescription/StoryDescription';
import { PageableResponse } from '@api/common/pageableResponse';
import {
    fetchBanners,
    BannerResponse,
} from '@api/services/story-service/bannerService';
import {
    fetchStoryById,
    StoryResponse,
} from '@api/services/story-service/storyService';
import { ApiResponse } from '@api/common/apiResponse';
import {
    ChapterResponse,
    fetchChapters,
} from '@api/services/story-service/chapterService';
import { DEFAULT } from '@api/common/defaultConstants';

const StoryDetail = () => {
    const [banners, setBanners] = useState<PageableResponse<BannerResponse>>();
    const [error, setError] = useState<string | null>(null);
    const [story, setStory] = useState<ApiResponse<StoryResponse>>();
    const [chapters, setChapters] =
        useState<PageableResponse<ChapterResponse>>();

    const { nameWithId } = useParams<{ nameWithId: string }>();

    const loadBanners = async () => {
        try {
            const data = await fetchBanners();
            setBanners(data.data);
        } catch (err) {
            setError('Unable to fetch Banners. Please try again later.');
        }
    };

    const loadStory = async () => {
        const storyId = nameWithId ? nameWithId.split('-').pop() : '';

        try {
            const data = storyId ? await fetchStoryById(storyId) : null;
            if (_.isEmpty(data?.data)) {
                setError(
                    data?.message ||
                        'Unable to fetch story detail. Please try again later.'
                );
                return;
            }
            setStory(data);
        } catch (err) {
            setError('Unable to fetch story detail. Please try again later.');
        }
    };

    const loadChapters = async () => {
        const storyId = nameWithId ? nameWithId.split('-').pop() : '';
        try {
            const data = storyId
                ? await fetchChapters(
                      DEFAULT.PAGE_NUMBER_DEFAULT,
                      DEFAULT.PAGE_SIZE_MAX,
                      storyId
                  )
                : null;
            if (_.isEmpty(data?.data)) {
                setError(
                    data?.message ||
                        'Unable to fetch Chapter. Please try again later.'
                );
                return;
            }
            setChapters(data.data);
        } catch (err) {
            setError('Unable to fetch Chapters. Please try again later.');
        }
    };

    useEffect(() => {
        loadBanners();
        loadStory();
        loadChapters();
    }, []);

    if (error) return <div>{error}</div>;

    return (
        <>
            <Banner
                imageUrl={banners?.data?.[7]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[7]?.url +
                        '-' +
                        banners?.data?.[7]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[7]?.name || ''}
            />
            {story && <StoryInfo storyProps={story.data} />}
            {chapters && <ListChapter chapterListProps={chapters} />}
            <Banner
                imageUrl={banners?.data?.[9]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[9]?.url +
                        '-' +
                        banners?.data?.[9]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[9]?.name || ''}
            />
            {story && <StoryDescription storyProps={story.data} />}
            <RelatedStory />
            <Banner
                imageUrl={banners?.data?.[8]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[8]?.url +
                        '-' +
                        banners?.data?.[8]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[8]?.name || ''}
            />
            <RatingStory />
        </>
    );
};

export default StoryDetail;
