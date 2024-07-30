import clsx from "clsx";
import { images } from "~/assets/images";
import styles from "./DiscoverItem.module.scss";

const DiscoverItem = (props) => {
	return (
		<div className={clsx(styles.discoverItem)}>
			{props?.type === "hashtag" ? (
				<img src={images.hashtagIcon} alt="hashtag" />
			) : (
				<img src={images.musicIcon} alt="music" />
			)}
			<p>{props.text}</p>
		</div>
	);
};

export default DiscoverItem;
