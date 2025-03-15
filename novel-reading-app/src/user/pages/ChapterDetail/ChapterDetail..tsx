import React, { use, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import _ from 'lodash';
import Banner from '@userComponents/Banner/Banner';
import ChapterContent from '@userComponents/ChapterContent/ChapterContent';
import ChapterNavigation from '@userComponents/ChapterNavigation/ChapterNavigation';
import StoryComment from '@userComponents/StoryComment/StoryComment';
import { PageableResponse } from '@api/common/pageableResponse';
import {
    fetchBanners,
    BannerResponse,
} from '@api/services/story-service/bannerService';
import {
    fetchStoryById,
    StoryResponse,
} from '@api/services/story-service/storyService';
import {
    ChapterResponse,
    fetchChapterById,
    fetchSimpleChapter,
} from '@api/services/story-service/chapterService';
import { ApiResponse } from '@api/common/apiResponse';

const ChapterDetail: React.FC = () => {
    const [banners, setBanners] = useState<PageableResponse<BannerResponse>>();
    const [story, setStory] = useState<StoryResponse>();
    const [chapter, setChapter] = useState<ChapterResponse>();
    const [chapterList, setChapterList] =
        useState<ApiResponse<Array<ChapterResponse>>>();
    const [error, setError] = useState<string | null>(null);

    const { nameWithId, chapterId } = useParams<{
        nameWithId: string;
        chapterId: string;
    }>();

    const fetchSimpleChapterListData = async () => {
        const storyId = nameWithId ? nameWithId.split('-').pop() : '';
        try {
            const data = storyId ? await fetchSimpleChapter(storyId) : null;
            if (!data || _.isEmpty(data.data)) {
                setError(
                    data?.message ||
                        'Unable to fetch List Chapter Simple. Please try again later.'
                );
                return;
            }
            setChapterList(data);
        } catch (err) {
            setError(
                'Unable to fetch List Chapter Simple. Please try again later.'
            );
        }
    };

    useEffect(() => {
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
                setStory(data.data);
            } catch (err) {
                setError(
                    'Unable to fetch story detail. Please try again later.'
                );
            }
        };

        const loadChapter = async () => {
            try {
                const data = chapterId
                    ? await fetchChapterById(chapterId)
                    : null;
                if (_.isEmpty(data?.data)) {
                    setError(
                        data?.message ||
                            'Unable to fetch Chapter. Please try again later.'
                    );
                    return;
                }
                setChapter(data.data);
            } catch (err) {
                setError('Unable to fetch Chapters. Please try again later.');
            }
        };

        loadBanners();
        loadStory();
        loadChapter();
    }, []);

    if (error) return <div>{error}</div>;

    return (
        <>
            <Banner
                imageUrl={banners?.data?.[0]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[0]?.url +
                        '-' +
                        banners?.data?.[0]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[0]?.name || ''}
            />
            {story && chapter && (
                <ChapterContent
                    storyInfo={story}
                    chapterInfo={chapter}
                    chapterData={{
                        chapterList: chapterList,
                        onFetchChapterList: fetchSimpleChapterListData,
                    }}
                />
            )}
            <Banner
                imageUrl={banners?.data?.[1]?.bannerDesktop || ''}
                linkUrl={
                    banners?.data?.[1]?.url +
                        '-' +
                        banners?.data?.[1]?.bannerLinkedId || ''
                }
                altText={banners?.data?.[1]?.name || ''}
            />
            <ChapterNavigation />
            <StoryComment />
        </>
    );
};
export default ChapterDetail;
