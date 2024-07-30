import clsx from "clsx";
import { useEffect, useState } from "react";
import AccountItem from "~/components/AccountItem";
import styles from "./Sidebar.module.scss";
import { images } from "~/assets/images";
import Button from "~/components/Button";
import DiscoverItem from "../../components/DiscoverItem";
import Footer from "../Footer";

const Sidebar = () => {
	const [suggestedAccounts, setSuggestedAccounts] = useState([]);
	const [showAll, setShowAll] = useState(false);
	const [discoverList, setDiscoverList] = useState([]);

	useEffect(() => {
		//fake api
		const apiResult = [
			{
				username: "nguyenvana.offical",
				fullname: "Nguyen Van A",
				images: "https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg",
				isTick: true,
				followers: "6.7M",
				likes: "429.9M",
			},
			{
				username: "nguyenvana.offical",
				fullname: "Nguyen Van A",
				images: "https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg",
				isTick: true,
				followers: "6.7M",
				likes: "429.9M",
			},
			{
				username: "nguyenvana.offical",
				fullname: "Nguyen Van A",
				images: "https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg",
				isTick: true,
				followers: "6.7M",
				likes: "429.9M",
			},
			{
				username: "nguyenvana.offical",
				fullname: "Nguyen Van A",
				images: "https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg",
				isTick: true,
				followers: "6.7M",
				likes: "429.9M",
			},
			{
				username: "nguyenvana.offical",
				fullname: "Nguyen Van A",
				images: "https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg",
				isTick: true,
				followers: "6.7M",
				likes: "429.9M",
			},
			{
				username: "nguyenvana.offical",
				fullname: "Nguyen Van A",
				images: "https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg",
				isTick: true,
				followers: "6.7M",
				likes: "429.9M",
			},
			{
				username: "nguyenvana.offical",
				fullname: "Nguyen Van A",
				images: "https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg",
				isTick: true,
				followers: "6.7M",
				likes: "429.9M",
			},
			{
				username: "nguyenvana.offical",
				fullname: "Nguyen Van A",
				images: "https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg",
				isTick: true,
				followers: "6.7M",
				likes: "429.9M",
			},
		];

		const apiDiscover = [
			{
				type: "hashtag",
				text: "suthatla",
			},
			{
				type: "hashtag",
				text: "mackedoi",
			},
			{
				type: "hashtag",
				text: "sansangthaydoi",
			},
			{
				type: "music",
				text: "Yêu Đơn Phương Là Gì (MEE Remix) - Mee Media & h0n & BHMedia",
			},
			{
				type: "music",
				text: "Về Nghe Mẹ Ru - NSND Bach Tuyet & Hứa Kim Tuyền & 14 Casper & Hoàng Dũng",
			},
			{
				type: "music",
				text: "Thiên Thần Tình Yêu - RICKY STAR",
			},
		];

		setSuggestedAccounts(apiResult);
		setDiscoverList(apiDiscover);
		console.log(">>call api");
	}, []);

	return (
		<aside className={clsx(styles.wrapper)}>
			{/* NavBar */}
			<div className={clsx(styles.navBar, styles.container)}>
				<div className={clsx(styles.navBarContent, styles.home)}>
					<img src={images.homeIcon} alt="home" />
					For You
				</div>
				<div className={clsx(styles.navBarContent)}>
					<img src={images.followingIcon} alt="following" />
					Following
				</div>
				<div className={clsx(styles.navBarContent)}>
					<img src={images.liveIcon} alt="live" />
					LIVE
				</div>
			</div>

			{/* If user not login */}
			<div className={clsx(styles.unLogin, styles.container)}>
				<p>
					Log in to follow creators, like videos, and view comments.
				</p>
				<div className={clsx(styles.loginBtn)}>
					<Button outline>Log in</Button>
				</div>
			</div>
			{/* endif */}

			{/* Suggested accounts */}
			<div className={clsx(styles.suggestedAccounts, styles.container)}>
				<h4 className={clsx(styles.title)}>Suggested accounts</h4>
				<div className={clsx(styles.listAccounts)}>
					{suggestedAccounts.map((item, index) => {
						if (showAll === false && index >= 5) {
							return "";
						}
						return (
							<AccountItem key={index} accountInfo={item} hover />
						);
					})}
				</div>
				{suggestedAccounts.length > 5 && (
					<span
						className={clsx(styles.showAll)}
						onClick={() => setShowAll(!showAll)}
					>
						{showAll ? "See less" : "See all"}
					</span>
				)}
			</div>

			{/* Discover */}
			<div className={clsx(styles.discover, styles.container)}>
				<h4 className={clsx(styles.title)}>Discover</h4>
				<div className={clsx(styles.discoverList)}>
					{discoverList.length > 0 &&
						discoverList.map((item, index) => (
							<DiscoverItem
								key={index}
								text={item.text}
								type={item.type}
							/>
						))}
				</div>
			</div>

			{/* Footer */}
			<Footer />
		</aside>
	);
};

export default Sidebar;
