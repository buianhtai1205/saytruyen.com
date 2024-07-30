import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { publicRoutes } from "./routes";
import { DefaultLayout } from "~/components/Layout";
import { Fragment } from "react";

function App() {
	return (
		<Router>
			<div className="App">
				<Routes>
					{publicRoutes.map((route, index) => {
						let Layout =
							route.layout !== undefined
								? route.layout
								: DefaultLayout;
						if (Layout === null) Layout = Fragment;
						const Page = route.component;
						return (
							<Route
								key={index}
								path={route.path}
								element={
									<Layout>
										<Page />
									</Layout>
								}
							/>
						);
					})}
				</Routes>
			</div>
		</Router>
	);
}

export default App;
