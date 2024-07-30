import { useEffect, useState } from "react";
import clsx from "clsx";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
	faCommentDots,
	faHeart,
	faShare,
} from "@fortawesome/free-solid-svg-icons";
import { ref, child, get } from "firebase/database";
import { database } from "~/firebase-server";
import styles from "./ContentItem.module.scss";
import { Link } from "react-router-dom";
import Button, { ActionButton } from "../Button";
import { images } from "~/assets/images";

const ContentItem = (props) => {
	const [user, setUser] = useState();
	const [video, setVideo] = useState("");

	useEffect(() => {
		const dbRef = ref(database);
		get(child(dbRef, `0/videos/${props.id}`))
			.then((snapshot) => {
				if (snapshot.exists()) {
					setVideo(snapshot.val());
					const userId = snapshot.val().userId;
					get(child(dbRef, `0/users/${userId}`)).then((snapshot) => {
						if (snapshot.exists()) {
							setUser(snapshot.val());
						} else {
							console.log("No data user available");
						}
					});
				} else {
					console.log("No data video available");
				}
			})
			.catch((error) => {
				console.error(error);
			});
	}, [props.id]);

	return (
		<div className={clsx(styles.wrapper)}>
			<Link>
				<img
					src="https://img.meta.com.vn/Data/image/2022/01/06/avatar-tiktok-6.jpg"
					alt="avatar"
					className={clsx(styles.avatar)}
				/>
			</Link>
			<div className={clsx(styles.contentContainer)}>
				<div className={clsx(styles.textInfoContainer)}>
					<Link className={clsx(styles.authContainer)}>
						<div className={clsx(styles.username)}>
							{user?.username}
						</div>
						<div className={clsx(styles.fullName)}>
							{user?.name}
						</div>
					</Link>
					<div className={clsx(styles.followButton)}>
						<Button outline>Follow</Button>
					</div>
					<div className={clsx(styles.captionContainer)}>
						{video.caption}
					</div>
					<div className={clsx(styles.musicContainer)}>
						<Link>
							<img src={images.musicIcon} alt="music" /> nhạc nền
							- Người viết tình ca
						</Link>
					</div>
				</div>
				<div className={clsx(styles.videoContainer)}>
					<iframe
						title={video.urlId}
						id="ytplayer"
						type="text/html"
						width="336"
						height="578"
						src={`https://www.youtube.com/embed/${video.urlId}?autoplay=1&origin=http://example.com`}
						frameborder="0"
					></iframe>
					<div className={clsx(styles.actionItem)}>
						<ActionButton count={"103.5k"}>
							<FontAwesomeIcon
								icon={faHeart}
								className={clsx(styles.icon)}
							/>
						</ActionButton>
						<ActionButton count={"937"}>
							<FontAwesomeIcon
								icon={faCommentDots}
								className={clsx(styles.icon)}
							/>
						</ActionButton>
						<ActionButton count={"151"}>
							<FontAwesomeIcon
								icon={faShare}
								className={clsx(styles.icon)}
							/>
						</ActionButton>
					</div>
				</div>
			</div>
		</div>
	);
};

export default ContentItem;
