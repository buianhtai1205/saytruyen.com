import clsx from "clsx";
import ContentItem from "~/components/ContentItem";
import styles from "./Home.module.scss";

const Home = () => {
	return (
		<div className={clsx(styles.wrapper)}>
			<div className={clsx(styles.listContents)}>
				<ContentItem id={"video1"} />
				<ContentItem id={"video2"} />
				<ContentItem id={"video1"} />
				<ContentItem id={"video2"} />
			</div>
		</div>
	);
};

export default Home;
