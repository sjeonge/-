import React, {useState, useCallback} from 'react';
import {View} from 'react-native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import MypageTemplate from '../templates/MypageTemplate';
import Filter from '@assets/filter.svg';
import Home from '@assets/home.svg';

import MyPostList from '@pages/MyPostList';
import MyLikedList from '@pages/MyLikedList';
import MyLikedSpots from '@pages/MyLikedSpots';
import MyWalkingRecord from '@pages/MyWalkingRecord';
import MyDogs from '@pages/MyDogs';
import MapViewAlone from '@pages/MapViewAlone';
import LogView from '@pages/LogView';
import WalkReview from './WalkReview';
import {useSelector} from 'react-redux';
import {RootState} from '~/store/reducer';

//UserInfoType
export type UserInfoType = {
  imageSource: string;
  userName: string;
  walkNumber: number;
  walkHour: number;
  walkDistance: number;
};

const MypageStack = createNativeStackNavigator();
export const MypageStackNavigator = () => (
  <MypageStack.Navigator>
    <MypageStack.Screen
      name="MypageInit"
      component={Mypage}
      options={{headerShown: false}}
    />
    <MypageStack.Screen name="MyPostList" component={MyPostList} />
    <MypageStack.Screen name="MyLikedList" component={MyLikedList} />
    <MypageStack.Screen name="MyLikedSpots" component={MyLikedSpots} />
    <MypageStack.Screen name="MyWalkingRecord" component={MyWalkingRecord} />
    <MypageStack.Screen name="MyDogs" component={MyDogs} />
    <MypageStack.Screen
      name="MapViewAlone"
      component={MapViewAlone}
      options={{headerShown: true}}
    />
    <MypageStack.Screen
      name="LogView"
      component={LogView}
      options={{headerShown: true}}
    />
    <MypageStack.Screen
      name="WalkReview"
      component={WalkReview}
      options={{
        headerTitle: '상대방 이름',
        headerTitleAlign: 'center',
        headerTitleStyle: {
          fontWeight: 'bold',
          fontSize: 15,
        },
      }}
    />
  </MypageStack.Navigator>
);

// 마이페이지 버튼탭 목록(navi동작)
const myPageListNavi = [
  {
    name: 'MyPostList',
    icon: <Filter width={25} height={25} fill={'black'} stroke={'black'} />,
    btnText: '내가 쓴 글',
  },
  {
    name: 'MyLikedList',
    icon: <Home width={25} height={25} fill={'black'} stroke={'black'} />,
    btnText: '내가 찜한 글',
  },
  {
    name: 'MyLikedSpots',
    icon: <Home width={25} height={25} fill={'black'} stroke={'black'} />,
    btnText: '내가 찜한 스팟',
  },
  {
    name: 'MyWalkingRecord',
    icon: <Home width={25} height={25} fill={'black'} stroke={'black'} />,
    btnText: '내 산책 기록',
  },
  {
    name: 'MyDogs',
    icon: <Home width={25} height={25} fill={'black'} stroke={'black'} />,
    btnText: '내 강아지',
  },
  {
    name: 'WalkReview',
    icon: <Home width={25} height={25} fill={'black'} stroke={'black'} />,
    btnText: '산책후기',
  },
];
// 마이페이지 버튼탭 목록(다른동작)
const myPageListFunc = [
  {
    name: 'Logout',
    icon: <Home width={25} height={25} fill={'black'} stroke={'black'} />,
    btnText: '로그아웃',
  },
];

function Mypage({navigation}: any) {
  const user = useSelector((state: RootState) => state.user);
  const userInfo: UserInfoType = {
    imageSource: user.profileImage,
    userName: user.nickname,
    walkNumber: 10,
    walkHour: 10,
    walkDistance: 100,
  };
  const [isEditing, setIsEditing] = useState(false);
  const [tempNickname, setTempNickname] = useState(userInfo.userName);

  const onChangeNickname = useCallback(text => {
    setTempNickname(text);
  }, []);

  const profileEdit = (): void => {
    if (isEditing) {
      // 변경 profile 전송(닉네임 + 사진)
    }
    setIsEditing(!isEditing);
  };

  return (
    <View>
      <MypageTemplate
        userInfo={userInfo}
        isEditing={isEditing}
        profileEdit={profileEdit}
        onChangeNickname={onChangeNickname}
        value={tempNickname}
        TabButtonListNavi={myPageListNavi}
        TabButtonListFunc={myPageListFunc}
        navigation={navigation}
      />
    </View>
  );
}

export default Mypage;