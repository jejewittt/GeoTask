package com.geotask.myapplication.Controllers;

import android.os.AsyncTask;

import com.geotask.myapplication.Controllers.ArgumentWrappers.AsyncArgumentWrapper;
import com.geotask.myapplication.DataClasses.GTData;

import java.io.IOException;

public class MasterController {

    static ElasticsearchController controller = new ElasticsearchController();

    public static void verifySettings() {
        controller.verifySettings();
    }

    public static void setTestSettings(String testSettings) {
        controller.setTestSettings(testSettings);
    }

    public static void createIndex() throws IOException {
        controller.createIndex();
    }

    public static void deleteIndex() throws IOException {
        controller.deleteIndex();
    }

    public static void shutDown() {
        controller.shutDown();
    }

    //ToDo
    public static boolean emailNotUsed(String userEmail) {
        return true;
    }

    public static class AsyncCreateNewDocument extends AsyncTask<GTData, Void, Void> {

        @Override
        protected Void doInBackground(GTData... dataList) {
            controller.verifySettings();

            for(GTData data : dataList) {
                try {
                    controller.createNewDocument(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    //ToDo: return document
    public static class AsyncGetDocument extends AsyncTask<AsyncArgumentWrapper, Void, Void> {

        @Override
        protected Void doInBackground(AsyncArgumentWrapper... argumentList) {
            controller.verifySettings();

            for (AsyncArgumentWrapper argument : argumentList) {
                try {
                    controller.getDocument(argument.getID(), argument.getType());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


        }
    }

    public static class AsyncDeleteDocument extends  AsyncTask<AsyncArgumentWrapper, Void, Void> {

        @Override
        protected Void doInBackground(AsyncArgumentWrapper... argumentList) {
            controller.verifySettings();

            for(AsyncArgumentWrapper argument : argumentList) {
                try {
                    controller.deleteDocument(argument.getID(), argument.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public static class AsyncUpdateDocument extends AsyncTask<GTData, Void, Void> {

        @Override
        protected Void doInBackground(GTData... dataList) {
            controller.verifySettings();

            for(GTData data: dataList) {
                try {
                    controller.updateDocument(data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    public static class AsyncSearch extends AsyncTask<AsyncArgumentWrapper, Void, Void> {

        @Override
        protected Void doInBackground(AsyncArgumentWrapper... argumentWrappers) {
            controller.verifySettings();

            for(AsyncArgumentWrapper argument : argumentWrappers) {
                try {
                    controller.search(argument.getSearchQuery(), argument.getType());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}