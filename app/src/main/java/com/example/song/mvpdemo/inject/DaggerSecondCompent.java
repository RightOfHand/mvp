package com.example.song.mvpdemo.inject;

import com.example.song.mvpdemo.SecondActivity;
import com.example.song.mvpdemo.SecondActivity_MembersInjector;
import com.example.song.mvpdemo.bean.SimpleModule;
import com.example.song.mvpdemo.bean.SimpleModule_ProvideStudentFactory;
import com.example.song.mvpdemo.bean.StudentBean;
import com.example.song.mvpdemo.bean.StudentBean_Factory;

import javax.inject.Provider;

import dagger.MembersInjector;
import dagger.internal.Preconditions;

/**
 * Description:
 * Created by song on 2018/3/12.
 * email：bjay20080613@qq.com
 */

public final class DaggerSecondCompent implements SimpleFactoryActivityComponent{

    private SimpleModule simpleModule;
    private Provider<StudentBean> provideStudentProvider;

    private MembersInjector<SecondActivity> secondActivityMembersInjector;

    public DaggerSecondCompent(Builder build) {
        this.simpleModule = build.simpleModule;
        initialize(build);
    }

    public static Builder build(){
        return new Builder();
    }

    @SuppressWarnings("unchecked")
    private void initialize(final Builder builder) {
          this.provideStudentProvider= SimpleModule_ProvideStudentFactory.create(builder.simpleModule);
          this.secondActivityMembersInjector= SecondActivity_MembersInjector.create(provideStudentProvider);
    }
    @Override
    public void inject(SecondActivity secondActivity) {
       secondActivityMembersInjector.injectMembers(secondActivity);
    }

    public static final class Builder{
        private SimpleModule simpleModule;

        private Builder() {}

        public SimpleFactoryActivityComponent build() {
            if (simpleModule == null) {
                throw new IllegalStateException(StudentBean.class.getCanonicalName() + " must be set");
            }
            return new DaggerSecondCompent(this);
        }

        public Builder module(SimpleModule simpleModule) {
            this.simpleModule = Preconditions.checkNotNull(simpleModule);
            return this;
        }
    }

}

